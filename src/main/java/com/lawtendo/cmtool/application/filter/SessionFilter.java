package com.lawtendo.cmtool.application.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.lawtendo.cmtool.application.DAO.SessionDAO;
import com.lawtendo.cmtool.application.DAO.UserDAO;
import com.lawtendo.cmtool.application.helper.Permission;
import com.lawtendo.cmtool.application.helper.SessionHelper;
import com.lawtendo.cmtool.application.service.SessionService;
import com.lawtendo.cmtool.application.service.UserService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.utils.Utils;


@Component
@WebFilter
public class SessionFilter implements Filter  {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);	
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	Response responseObj;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		String header = request.getHeader(Utils.HEADER_AUTH);
		logger.info("Session Filter");
		
		String requestedURI = request.getRequestURI().toString();
		logger.info(requestedURI);
		logger.info("header " + header);
		
		
		if(header != null) {
	
			String[] tokenArr = header.split("\\s");
			String token = tokenArr.length > 1 && !tokenArr[1].equals("null") ? tokenArr[1] : null;
	
		//checking token header
		if(token != null) {
			
			logger.info("token found");
			

			SessionDAO sessionObj = sessionService.findByToken(token);
			if(sessionObj != null) {
				
				UserDAO userObj = userService.findByEmail(sessionObj.getEmail());
				
				//token verification for "default" auth
				if(userObj != null && userObj.getAuth().equals(Utils.AUTH_DEFAULT)) {
					logger.info("default");
					String verifiedToken = sessionHelper.verifyToken(token);
					
					if(verifiedToken != null) {
						Map<String, Object> claimMap = sessionHelper.decodeToken(token);
						
						//verifying token claims with session in db 
						if(sessionObj.getUuid().equals(claimMap.get(Utils.UUID)) &&
								sessionObj.getEmail().equals(claimMap.get(Utils.EMAIL)) &&
								sessionObj.getRole().equals(claimMap.get(Utils.ROLE))) {
							
							logger.info("token claims found in session db");
							
							String role = sessionObj.getRole();
							logger.info(role);
							logger.info(requestedURI);
							if(checkRights(role, requestedURI)) {
								
								filterChain.doFilter(request, response);
								
							}else {
								logger.error("Forbidden to access");
								response.sendError(403, "Forbidden to access");
							}
					}else {
						//if token claims not found in session db
						logger.error("Invalid token");
						
//						response.sendError(401, "Invalid token");
						
						responseObj.setData(null);
						responseObj.setStatusCode("200");
						responseObj.setMessage("Invalid Token");
						
						response.setContentLength(responseObj.toJson().length());
						response.getWriter().write(responseObj.toJson());
					}
				}else {
					//if token is not verified
					logger.error("Invalid token");
					sessionService.deleteByToken(token);
					
					responseObj.setData(null);
					responseObj.setStatusCode("200");
					responseObj.setMessage("Invalid Token");
					
					response.setContentLength(responseObj.toJson().length());
					response.getWriter().write(responseObj.toJson());
					
				}
			}
				
				//token verification for "google" auth
				if(userObj.getAuth().equals(Utils.AUTH_GOOGLE)) {
					logger.info("google");
					GoogleIdToken googleVerifiedToken = sessionHelper.verifyGoogleToken(token);
					
					if(googleVerifiedToken != null) {
						Map<String, Object> claimMap = sessionHelper.decodeGoogleToken(googleVerifiedToken);
						
						//verifying token claims with session in db 
						if(sessionObj.getUuid().equals(userObj.getUuid()) &&
								sessionObj.getEmail().equals(claimMap.get(Utils.EMAIL)) &&
								sessionObj.getRole().equals(userObj.getRole())) {
							
							logger.info("token claims found in session db");
							
							String role = sessionObj.getRole();
							logger.info(role);
							logger.info(requestedURI);
							if(checkRights(role, requestedURI)) {
								
//								move to controller
								logger.info("move to controller");
								filterChain.doFilter(request, response);
								
							}else {
								logger.error("Forbidden to access");
								response.sendError(403, "Forbidden to access");
							}
					}else {
						//if token claims not found in session db
						logger.error("Invalid token");
//						response.sendError(401, "Invalid token");
						
						responseObj.setData(null);
						responseObj.setStatusCode("200");
						responseObj.setMessage("Invalid Token");
						
						response.setContentLength(responseObj.toJson().length());
						response.getWriter().write(responseObj.toJson());
					}
						
					}else {
						//if token is not verified
						logger.error("Invalid token");
						sessionService.deleteByToken(token);
//						response.sendError(401, "Invalid token");
						
						responseObj.setData(null);
						responseObj.setStatusCode("200");
						responseObj.setMessage("Invalid Token");
						
						response.setContentLength(responseObj.toJson().length());
						response.getWriter().write(responseObj.toJson());
					}
				}
				
			}else {
				//if session doesn't exist with httpSession token in db
				logger.error("Invalid token");
//				response.sendError(401, "Invalid token");
				
				responseObj.setData(null);
				responseObj.setStatusCode("200");
				responseObj.setMessage("Invalid Token");
				
				response.setContentLength(responseObj.toJson().length());
				response.getWriter().write(responseObj.toJson());
			}
		
		}else {
			//if session not found
			logger.info("session not found");
			if(checkRights(Utils.ROLE_PUBLIC, requestedURI)){
				
				logger.info("rights found");
				filterChain.doFilter(request, response);
				
			}else {
				
				logger.error("rights not found");

				response.sendError(404, "Invalid Request URL");
			}
		}
		
		}

				
	}
	
	private boolean checkRights(String role, String requestedURI) {
		
		boolean isValid = false;
		List<String> rights = Permission.getRights(role);
		for(String right : rights) {
			if(right.equals(requestedURI)) {
				isValid = right.equals(requestedURI);
				break;
			}
		}
						
		return isValid;
	}

}
