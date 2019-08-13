package com.lawtendo.cmtool.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.SessionDAO;
import com.lawtendo.cmtool.application.repository.SessionRepo;

@Component
@Service
public class SessionService {

	@Autowired
	SessionRepo sessionRepo;
	
	public SessionDAO saveSession(SessionDAO sessionObj) {
		
		return sessionObj != null ? sessionRepo.save(sessionObj) : null;
	}
	
	public Long deleteByToken(String token) {
		if(token != null) {
			return sessionRepo.deleteByToken(token);
		}
		return null;
	}
	
	public SessionDAO findByToken(String token) {
		if(token != null) {
			SessionDAO sessionObj = sessionRepo.findByToken(token);
			
			return sessionObj != null ? sessionObj : null;
		}
		
		return null;
	}
	
	public SessionDAO findByUuidAndToken(String uuid, String token) {
		if(uuid != null && token != null) {
			SessionDAO sessionObj = sessionRepo.findByUuidAndToken(uuid, token);
			
			return sessionObj != null ? sessionObj : null;
		}
		return null;
	}
}
