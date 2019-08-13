package com.lawtendo.cmtool.application.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.lawtendo.cmtool.application.DAO.SessionDAO;
import com.lawtendo.cmtool.application.DAO.UserDAO;
import com.lawtendo.cmtool.application.DTO.UserDTO;
import com.lawtendo.cmtool.application.helper.SessionHelper;
import com.lawtendo.cmtool.application.service.SessionService;
import com.lawtendo.cmtool.application.service.UserService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.utils.Utils;
import com.lawtendo.cmtool.application.validations.UserValidator;

@RestController("/")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private SessionHelper sessionHelper;
	
	@Autowired
	private Response response;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/")
	public String check() {
		return "Running";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserDTO userDTO, HttpServletResponse httpResponse )  {
		 
		Boolean isEmailValid = UserValidator.validateEmail(userDTO.getEmail());
		Boolean isPasswordValid = UserValidator.validatePassword(userDTO.getPassword());
		
		logger.info("/login");
		if(isEmailValid && isPasswordValid) {
			
			logger.info("validation success");
			UserDAO userObj = userService.findByEmail(userDTO.getEmail());
			
			if(userObj != null) {
				logger.info(userObj.toString());
				logger.info("user found");
				
				if(userService.matchPassword(userDTO.getPassword(), userObj.getPassword())) {
					
					logger.info("password matched");
					SessionDAO sessionObj = sessionHelper.getSession(userObj.getEmail(), userObj.getRole(), userObj.getUuid());
					
					httpResponse.setHeader(Utils.HEADER_AUTH,"token " +  sessionObj.getToken());

		
					logger.info(sessionObj.toString());
					if(sessionService.saveSession(sessionObj) != null) {
						
						response.setStatusCode("200");
						response.setMessage("Logged in Success");
						response.setData(userObj.getUuid());
					}else {
						
						response.setStatusCode("200");
						response.setMessage("Unable to login");
						response.setData(null);
					}
					
				}else {
					logger.info("password not matched");
					response.setStatusCode("200");
					response.setMessage("Invalid credentials");
					response.setData(null);
				}
			}
				else {
				logger.info("email not found");
				response.setStatusCode("200");
				response.setMessage("Not Found");
				response.setData(null);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("Validation failed");
			response.setData(null);
		}
		
		return response.toJson();
	}
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserDTO userDTO) {
		
		logger.info("/signup");
		logger.info(userDTO.toString());
		
		List<String> validations = UserValidator.isValid(userDTO);
		
		if(validations.size() == 0) {
			
			UserDAO existingUser = userService.findByEmailAndAuth(userDTO.getEmail(),Utils.AUTH_DEFAULT);
			if(existingUser != null) {
				logger.info("User already Exists");
				response.setStatusCode("200");
				response.setMessage("Already Exists");
				response.setData(null);
				
			}else {
				
				UserDAO userDAO = new UserDAO();
				userDAO.setEmail(userDTO.getEmail());
				userDAO.setUserName(userDTO.getUserName());
				userDAO.setFirstName(userDTO.getFirstName());
				userDAO.setLastName(userDTO.getLastName());
				userDAO.setMobileNo(userDTO.getPassword());
				
				UserDAO userObj = userService.saveUser(userDAO);
				if(userObj != null) {
					
					logger.info("signup success");
					
					response.setStatusCode("200");
					response.setMessage("Signup success");
					response.setData(userObj);
					
				}else {
					logger.info("unable to register");
					response.setStatusCode("200");
					response.setMessage("Unable to register");
					response.setData(null);
					
				}
			}
		}else {
			
			logger.info("validation failed");
			
			response.setStatusCode("200");
			response.setMessage("Validation failed");
			response.setData(validations);
			
		}
		
		return response.toJson();
	}
	
	@PostMapping("/google-auth")
	public String googleAuth(@RequestBody UserDTO userDTO,HttpServletResponse httpResponse ) {
		
		String idTokenString = userDTO.getPassword();
		logger.info(idTokenString);
		if(idTokenString != null) {
			
			GoogleIdToken idToken = sessionHelper.verifyGoogleToken((String)idTokenString);
		
			if(idToken != null) {
				
				Map<String, Object> payloadMap = sessionHelper.decodeGoogleToken(idToken);
				UserDAO userObj = userService.findByEmail(userDTO.getEmail());
				
				if(userObj != null) {
					
					//if user already exist
					SessionDAO sessionObj = sessionService.findByToken(idTokenString);
					logger.info("user existed already");
					if(sessionObj != null) {
						logger.info("session existed already");
						//if session already exist
						
						response.setStatusCode("200");
						response.setMessage("Logged in Success");
						response.setData(userObj.getUuid());
						httpResponse.setHeader(Utils.HEADER_AUTH,"token " +  sessionObj.getToken());
					}else {
						
						//if session doesn't exist, create
						logger.info("session updated successfully");
						sessionObj = sessionHelper.getAuthSession(userObj.getUuid(),
										userObj.getEmail(), userObj.getRole(), idTokenString,
										(Timestamp)payloadMap.get(Utils.EXPIRED_AT), 
										(Timestamp)payloadMap.get(Utils.CREATED_AT));
						
						if(sessionService.saveSession(sessionObj) != null) {
							//updating user's password with current auth-token
							userObj.setPassword(idTokenString);
							
							if(userService.saveUser(userObj) != null) {

								response.setStatusCode("200");
								response.setMessage("Logged in Success");
								response.setData(userObj.getUuid());
								httpResponse.setHeader(Utils.HEADER_AUTH,"token " +  sessionObj.getToken());
							}else {
								response.setStatusCode("200");
								response.setMessage("Unable to login");
								response.setData(null);
							}
							
						}else {
							response.setStatusCode("200");
							response.setMessage("Unable to login");
							response.setData(null);
						}
					}
					
				}else {
					
					UserDAO userDAO = new UserDAO();
					userDAO.setAuth(Utils.AUTH_GOOGLE);
					userDAO.setPassword(userDTO.getPassword());
					userDAO.setEmail(userDTO.getEmail());
					userDAO.setUserName(userDTO.getUserName());
					userDAO.setFirstName(userDTO.getFirstName());
					userDAO.setLastName(userDTO.getLastName());
					userDAO.setMobileNo(userDTO.getMobileNo());
					
					UserDAO userObj1 = userService.saveUser(userDAO);
					if(userObj1 != null) {
						logger.info("new user created");
						
						SessionDAO sessionObj = sessionHelper.getAuthSession(userObj1.getUuid(),
								userObj1.getEmail(), userObj1.getRole(), idTokenString,
								(Timestamp)payloadMap.get(Utils.EXPIRED_AT), 
								(Timestamp)payloadMap.get(Utils.CREATED_AT));
						
						if(sessionService.saveSession(sessionObj) != null) {
							
							logger.info("Session created successfully");
							response.setStatusCode("200");
							response.setMessage("Logged in Success");
							
							response.setData(userObj1.getUuid());
							
							logger.info(response.toJson());
							httpResponse.setHeader(Utils.HEADER_AUTH,"token " +  sessionObj.getToken());
						}else {
							logger.info("Unable to create session");
							response.setStatusCode("200");
							response.setMessage("Unable to login");
							response.setData(null);
						}
					}
					else {
						response.setStatusCode("200");
						response.setMessage("Unable to login");
						response.setData(null);
					}
				}
				
			}else {
				
				SessionDAO sessionObj = sessionService.findByToken(idTokenString);
				
				//delete expired session
				if(sessionObj != null) {
					sessionService.deleteByToken(idTokenString);
				}
				logger.error("delete expired session");
				response.setStatusCode("200");
				response.setMessage("Invalid token");
				response.setData(null);
			}
		}else {
			logger.error("Invalid token");
			response.setStatusCode("200");
			response.setMessage("Invalid token");
			response.setData(null);
		}
		return response.toJson();
	}

	@PostMapping("/logout")
	public String logout(@RequestBody UserDTO userDTO, HttpServletRequest httpRequest) {
		
		logger.info("logout controller");
		String header = httpRequest.getHeader(Utils.HEADER_AUTH);
		logger.info("header " + header);
		
		String token = header != null && header.split("\\s").length > 1 ? header.split("\\s")[1] : null;
		logger.info("token " + token);
		
		if(header != null && token != null) {
			SessionDAO sessionObj = sessionService.findByUuidAndToken(userDTO.getUuid(), token);
			
			if(sessionObj != null) {
				sessionService.deleteByToken(token);
				logger.info("Logged Out Successfully");
				response.setStatusCode("200");
				response.setMessage("Logged Out Successfully");
				response.setData(true);
			}else {
				logger.error("Session not Found");
				response.setStatusCode("200");
				response.setMessage("Session not Found");
				response.setData(false);
			}
		}else {
			logger.error("Header not Found");
			response.setStatusCode("200");
			response.setMessage("Header not Found");
			response.setData(false);
		}
		
		
		return response.toJson();
	}

	@PostMapping("/webindex")
	public String webIndex(@RequestBody UserDTO userDTO, HttpServletRequest httpRequest, HttpServletResponse httpResponse ) {
		logger.info("POST /webindex");
		String header = httpRequest.getHeader(Utils.HEADER_AUTH);
		
		if(header != null) {
			String token = header.split("\\s")[1];
			
			//checking token header
			if(token != null && !token.equals("null")) {
				
				UserDAO userObj = userService.findByUuid(userDTO.getUuid());
				if(userObj != null) {
					
					SessionDAO sessionObj = sessionService.findByUuidAndToken(userDTO.getUuid(), token);
					
					if(sessionObj != null) {
						logger.info("auth success !!!");
						response.setData(true);
						response.setMessage("auth success !!!");
						response.setStatusCode("200");
					}
				}else {
					logger.error("auth failed !!!");
					response.setData(null);
					response.setMessage("auth failed !!!");
					response.setStatusCode("200");
				}
			}else {
				logger.error("auth failed !!!");
				response.setData(null);
				response.setMessage("auth failed !!!");
				response.setStatusCode("200");
			}
		}
		
		return response.toJson();
	}

}
