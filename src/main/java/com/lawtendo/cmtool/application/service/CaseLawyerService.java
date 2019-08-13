package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.LawyerDAO;
import com.lawtendo.cmtool.application.repository.CaseLawyerRepo;

@Service
public class CaseLawyerService {
	
	@Autowired
	CaseLawyerRepo caseLawyerRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CaseLawyerService.class);
	
	public LawyerDAO save(LawyerDAO userObj) {
		logger.info("CaseLawyerService -> save()");
			userObj.setCreatedAt(getTimestamp());
			userObj.setUpdatedAt(getTimestamp());
			return caseLawyerRepo.save(userObj);
		}
	
	public List<LawyerDAO> findAll(){
		logger.info("CaseLawyerService -> getList()");
		return caseLawyerRepo.findAll();
	}
	
	public LawyerDAO findOne(String userId) {
		logger.info("CaseLawyerService -> getOne()");
		return caseLawyerRepo.findById(userId).orElse(null);
	}

	public void delete(LawyerDAO userObj) {
		logger.info("CaseLawyerService -> delete()");
		caseLawyerRepo.delete(userObj);
	}
	
	
	private Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
