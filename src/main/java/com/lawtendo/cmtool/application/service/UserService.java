package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.UserDAO;
import com.lawtendo.cmtool.application.repository.UserRepo;
import com.lawtendo.cmtool.application.utils.Utils;

@Component
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepo userRepo;
	
	
	public UserDAO saveUser(UserDAO userDAO) {
		
		if(userDAO.getEmail() != null || userDAO.getPassword() != null) {
			if(userDAO.getRole() == null) {
				userDAO.setRole(Utils.ROLE_GUEST);
			}
			
			String hashPassword = Utils.generateHash(userDAO.getPassword(), Utils.SECRET_KEY);
			userDAO.setPassword(hashPassword);
			
			if(userDAO.getAuth() == null) {
				userDAO.setAuth(Utils.AUTH_DEFAULT);
			}
			
			Timestamp timestamp = Utils.getTimestamp();
			userDAO.setUpdatedAt(timestamp);
			userDAO.setCreatedAt(timestamp);
			
			logger.info(userDAO.toString());
			
			
			UserDAO userObj = userRepo.save(userDAO);
	
			
			return userObj != null ? userObj : null; 
		}
		return null;
		
	}
	
	public void updateRole(String email, String role) {
		
	}
	
	public void updateUser(UserDAO userDAO) {
		
	}
	
	public UserDAO findByUuid(String uuid) {
		if(uuid != null) {
			UserDAO userObj = userRepo.findByUuid(uuid);
			
			return userObj != null ? userObj : null;
		}
		return null;
	}
	
	public UserDAO findByEmail(String email) {
		if(email != null) {
			UserDAO userObj = userRepo.findByEmail(email);
			return userObj != null ? userObj : null;
		}
		return null;
	}
	
	public UserDAO findByEmailAndAuth(String email, String auth) {
		if(email != null && auth != null) {
			UserDAO userObj = userRepo.findByEmailAndAuth(email, auth);
			
			return userObj != null ? userObj : null;
		}
		return null;
	}
	
	public Boolean matchPassword(String password, String hash) {
		
		if(password!= null && hash != null) {
			String hashedPassword = Utils.generateHash(password, Utils.SECRET_KEY);
			logger.info(hash);
			logger.info(hashedPassword);
			
			return hash.equals(hashedPassword);
		}
		return false;
	}
}
