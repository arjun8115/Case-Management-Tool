package com.lawtendo.cmtool.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.UserDAO;
import com.lawtendo.cmtool.application.DTO.UserDTO;
import com.lawtendo.cmtool.application.service.UserService;
import com.lawtendo.cmtool.application.utils.Response;

@RestController
@RequestMapping("/user")
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Response response;
	
	@Autowired
	private UserDTO userResponseDTO;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@PostMapping("/get")
	public String fetchUser(@RequestBody String uuid) {
		
		logger.info(uuid);
		UserDAO userObj = userService.findByUuid(uuid);
		
		if(userObj != null) {
			userResponseDTO.setUuid(uuid);
			userResponseDTO.setEmail(userObj.getEmail());
			userResponseDTO.setUserName(userObj.getUserName());
			userResponseDTO.setFirstName(userObj.getFirstName());
			userResponseDTO.setLastName(userObj.getLastName());
			userResponseDTO.setMobileNo(userObj.getMobileNo());
			
			logger.info("User Found !!!");
			response.setStatusCode("200");
			response.setMessage("User Found !!!");
			response.setData(userResponseDTO);
		
		}else {
			logger.error("User not Found !!!");
			response.setStatusCode("404");
			response.setMessage("User not Found !!!");
			response.setData(null);
		}
		return response.toJson();
	}

}
