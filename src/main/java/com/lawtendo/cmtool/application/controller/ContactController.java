package com.lawtendo.cmtool.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.ContactDAO;
import com.lawtendo.cmtool.application.DTO.ContactDTO;
import com.lawtendo.cmtool.application.service.CaseService;
import com.lawtendo.cmtool.application.service.ContactService;
import com.lawtendo.cmtool.application.utils.Response;

@Controller
@ResponseBody
@RestController
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	CaseService caseService;
	
	@Autowired
    Response response;
	
//	@Autowired
//	ContactDAO contactDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	
	@PostMapping("/create")
	public String createContact(@RequestBody ContactDTO contactDTO) {
		logger.info("POST /create");
		if(contactDTO.getUuid() != null && contactDTO.getCuid() != null) {
			if(caseService.findCaseByCuidAndUuid(contactDTO.getCuid(), contactDTO.getUuid()) != null && 
					caseService.findCaseByCuidAndUuid(contactDTO.getCuid(), contactDTO.getUuid()).isDeleted() == false) {
				ContactDAO contactDAO = new ContactDAO();
				contactDAO.setUuid(contactDTO.getUuid());
				contactDAO.setCuid(contactDTO.getCuid());
				contactDAO.setName(contactDTO.getName());
				contactDAO.setContactNumber(contactDTO.getContactNumber());
				contactDAO.setEmail(contactDTO.getEmail());
				
				if(contactService.saveContact(contactDAO) != null) {
					response.setStatusCode("200");
					response.setMessage("Contact Created Successfull !!!");
					response.setData("Contact Created Successfull !!!");
				}else {
					response.setStatusCode("200");
					response.setMessage("Unable to save");
					response.setData(null);
				}
			}else {
				response.setStatusCode("200");
				response.setMessage("Case Not Found !!!");
				response.setData(null);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("Credentials Not Found !!!");
			response.setData(null);
		}
		return response.toJson();
	}
	
	@PostMapping("/getAllByUuid")
	public String getContactByUuid(@RequestBody ContactDTO contactDTO){
		
		if(contactDTO.getUuid() != null) {
			List<ContactDAO> contacts = contactService.findContactByUuid(contactDTO.getUuid());
			
			List<ContactDAO> contactObj1 = new ArrayList<>();
			
			if(contacts.size() != 0) {
				for(ContactDAO item: contacts) {
					if(!item.isDeleted()) {
						contactObj1.add(item);
					}
				}
				
				response.setStatusCode("200");
				response.setMessage("Contact Found");
				response.setData(contactObj1);
			}else {
				response.setStatusCode("200");
				response.setMessage("No Contacts Found");
				response.setData(contactObj1);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("No Credential Found");
			response.setData(null);
		}
		return response.toJson();
		
	}
	
	@PostMapping("/getByCuidAndUuid")
	public String getContactByCuidAndUuid(@RequestBody ContactDTO contactDTO){
		logger.info("POST /getByCuidAndUuid");
		if(contactDTO.getUuid() != null && contactDTO.getCuid() != null) {
			List<ContactDAO> contacts = 
					contactService.findContactByCuidAndUuid(contactDTO.getCuid(), contactDTO.getUuid());
			
			List<ContactDAO> contactObj1 = new ArrayList<>();
			
			
			
			if(contacts != null && contacts.size() != 0) {
				
				for(ContactDAO item: contacts) {
					if(!item.isDeleted()) {
						contactObj1.add(item);
					}
				}
				
				logger.info("Contact Found");
				response.setStatusCode("200");
				response.setMessage("Contact Found");
				response.setData(contactObj1);
			}else {
				logger.info("No Contacts Found");
		
				response.setStatusCode("200");
				response.setMessage("No Contacts Found");
				response.setData(contactObj1);
			}
		}else {
			logger.error("No Credential Found");
			response.setStatusCode("200");
			response.setMessage("No Credential Found");
			response.setData(null);
		}
		return response.toJson();
	}	

	@PostMapping("/updateByCuidAndUuid")
	public String updateConatctByCuidAndUuid(@RequestBody ContactDTO contactDTO) {
		
		logger.info("POST /updateByCuidAndUuid");
		if(contactDTO.getUuid() != null && contactDTO.getCuid() != null && contactDTO.getConuid()!= null) {
			ContactDAO contactObj1 = contactService.findContactByConuid(contactDTO.getConuid());
			
			if(contactObj1.getUuid().equals(contactDTO.getUuid()) && contactObj1.getCuid().equals(contactDTO.getCuid())
					&& !contactObj1.isDeleted()) {
				
				if(contactDTO.getName() != null) {
					contactObj1.setName(contactDTO.getName());
				}
				
				if(contactDTO.getContactNumber() != null) {
					contactObj1.setContactNumber(contactDTO.getContactNumber());
				}
				
				if(contactDTO.getEmail() != null) {
					contactObj1.setEmail(contactDTO.getEmail());
				}
				
				if(contactService.updateContact(contactObj1) != null) {
					response.setStatusCode("200");
					response.setMessage("Contact updated Successfull !!!");
					response.setData("Contact updated Successfull !!!");
				}else {
					response.setStatusCode("200");
					response.setMessage("Unable to update");
					response.setData("unable to update");
				}
			}else {
				response.setStatusCode("200");
				response.setMessage("Invalid credential !!!");
				response.setData(null);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("No such contact !!!");
			response.setData(null);
		}
		return response.toJson();
	}
	
	@PostMapping("/deleteByConuid")
	public String deleteConatctByConuid(@RequestBody ContactDTO contactDTO) {
		
		logger.info("POST-> deleteByConuid");
		if(contactDTO.getConuid() != null) {
		ContactDAO contactObj = contactService.findContactByConuid(contactDTO.getConuid());
			 if( contactObj!= null && !contactObj.isDeleted()) {
				 contactObj.setDeleted(true);
				 if(contactService.saveContact(contactObj) != null) {
					 logger.info("Delete successfull !!!");
					 response.setStatusCode("200");
						response.setMessage("Delete successfull !!!");
						response.setData("Delete successfull !!!");
				 }else{
					 logger.info("Unable to delete !!!");
					response.setStatusCode("200");
					response.setMessage("Unable to delete !!!");
					response.setData(null);
				 }
			 }else {
				 logger.info("Contact not found !!!");
				response.setStatusCode("200");
				response.setMessage("Contact not found !!!");
				response.setData(null);
			 }
		}else {
			logger.info("Contact Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Contact Id not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}
}
