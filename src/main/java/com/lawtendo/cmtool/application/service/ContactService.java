package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.ContactDAO;
import com.lawtendo.cmtool.application.repository.ContactRepo;
import com.lawtendo.cmtool.application.utils.Utils;

@Service
public class ContactService {
	
	@Autowired
	ContactRepo contactRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
	
	public ContactDAO saveContact(ContactDAO contactDAO) {
		
		logger.info("ContactService -> saveContact()");
		
		Timestamp timestamp = Utils.getTimestamp();
		contactDAO.setUpdatedAt(timestamp);
		contactDAO.setCreatedAt(timestamp);
		
		ContactDAO contactObj = contactRepo.save(contactDAO);
		
		return contactObj != null ? contactObj : null;
	}
	
	public ContactDAO updateContact(ContactDAO contactDAO) {
		
		logger.info("ContactService -> saveContact()");
		
		Timestamp timestamp = Utils.getTimestamp();
		contactDAO.setUpdatedAt(timestamp);
		
		ContactDAO contactObj = contactRepo.save(contactDAO);
		
		return contactObj != null ? contactObj : null;
	}
	
	public ContactDAO findContactByConuid(String conuid) {
		logger.info("ContactService -> findContactByConuid()");
		if(conuid != null) {
			ContactDAO contactObj = contactRepo.findByConuid(conuid);
			
			return contactObj != null ? contactObj : null;
		}
		
		return null;
	}
	
	public List<ContactDAO> findContactByUuid(String uuid){
		logger.info("ContactService -> findByUuid()");
		if(uuid != null) {
			List<ContactDAO> contacts = contactRepo.findByUuid(uuid);
			return contacts != null ? contacts : null;
		}else {
			return null;
		}
		
	}
	
	public List<ContactDAO> findContactByCuidAndUuid(String cuid, String uuid){
		logger.info("ContactService -> findContactByCuidAndUuid()");
		if(uuid != null && cuid != null) {
			List<ContactDAO> contacts = contactRepo.findByCuidAndUuid(cuid, uuid);
			
			return contacts != null ? contacts : null;
		}else {
			return null;
		}
	}
	
	public Long deleteContactByConuid(String conuid) {
		logger.info("ContactService -> deleteContactByConuid()");
		if(conuid != null) {
			return contactRepo.deleteByConuid(conuid);
		}
		return null;
	}
	

}
