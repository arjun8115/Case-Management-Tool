package com.lawtendo.cmtool.application.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.CaseDAO;
import com.lawtendo.cmtool.application.DTO.CaseDTO;
import com.lawtendo.cmtool.application.service.CaseService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.validations.CaseValidator;



@RestController
@RequestMapping("/case")
public class CaseController {
	
	@Autowired
	Response response;
	
	@Autowired
	CaseService caseService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	@PostMapping("/create")
	public String createCase(@RequestBody CaseDTO caseDTO) {
		
		logger.info("POST /create");
		List <String> validations = CaseValidator.isValid(caseDTO);
		
		if(validations.size() == 0) {
			logger.info("Case Validation success");
			CaseDAO caseDAO = new CaseDAO();
			caseDAO.setCaseId(caseDTO.getCaseId());
			caseDAO.setCaseStatus(caseDTO.getCaseStatus());
			caseDAO.setCaseTitle(caseDTO.getCaseTitle());
			caseDAO.setCaseType(caseDTO.getCaseType());
			caseDAO.setCaseYear(caseDTO.getCaseYear());
			caseDAO.setCategory(caseDTO.getCategory());
			caseDAO.setClient(caseDTO.getClient());
			caseDAO.setRespondant(caseDTO.getRespondant());
			caseDAO.setForumId(caseDTO.getForumId());
			caseDAO.setDescription(caseDTO.getDescription());
			caseDAO.setUuid(caseDTO.getUuid());
			
			
			
			
			if(caseService.saveCase(caseDAO) != null) {
				logger.info("Created Successfully");
				response.setStatusCode("200");
				response.setMessage("Created Successfully ");
				response.setData("Created Successfully");
			}else {
				logger.error("Unable to create case");
				response.setStatusCode("200");
				response.setMessage("Unable to create case");
				response.setData(null);
			}
		}else {
			logger.error("Invalid request Obj !!!");
			response.setStatusCode("200");
			response.setMessage("Invalid request Obj !!!");
			response.setData(null);
		}
		return response.toJson();
	}
	
	@PostMapping("/getByCuidAndUuid")
	public String getCaseByCuidAndUuid(@RequestBody CaseDTO caseDTO){
		logger.info("POST /getCaseByCuidAndUuid");
		
		if(caseDTO.getCuid() != null && caseDTO.getUuid() != null) {
			CaseDAO caseObj1 = caseService.findCaseByCuidAndUuid(caseDTO.getCuid(), caseDTO.getUuid());
			
			
			if(caseObj1 != null && !caseObj1.isDeleted()) {
				
				CaseDTO caseResponseDTO = new CaseDTO();
				
				caseResponseDTO.setUuid(caseObj1.getUuid());
				caseResponseDTO.setCuid(caseObj1.getCuid());
				caseResponseDTO.setCaseId(caseObj1.getCaseId());
				caseResponseDTO.setCaseStatus(caseObj1.getCaseStatus());
				caseResponseDTO.setCaseTitle(caseObj1.getCaseTitle());
				caseResponseDTO.setCaseType(caseObj1.getCaseType());
				caseResponseDTO.setCaseYear(caseObj1.getCaseYear());
				caseResponseDTO.setCategory(caseObj1.getCategory());
				caseResponseDTO.setClient(caseObj1.getClient());
				caseResponseDTO.setRespondant(caseObj1.getRespondant());
				caseResponseDTO.setDescription(caseObj1.getDescription());
				caseResponseDTO.setForumId(caseObj1.getForumId());
				caseResponseDTO.setCreatedAt(caseObj1.getCreatedAt());
				caseResponseDTO.setUpdatedAt(caseObj1.getUpdatedAt());
				
				logger.info("Case Found Successfully !!!");
				response.setStatusCode("200");
				response.setMessage("Case Found Successfully !!!");
				response.setData(caseResponseDTO);
			}else {
				logger.error("No Case Found !!!");
				response.setStatusCode("200");
				response.setMessage("No Case Found !!!");
				List<Object> list = null;
				response.setData(list);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		
		return response.toJson();
		
	}
	
	@PostMapping("/updateByCuidAndUuid")
	public String updateCase(@RequestBody CaseDTO caseDTO){
		
		logger.info("POST /updateCase");
		if(caseDTO.getCuid() != null && caseDTO.getUuid() != null) {
			CaseDAO caseObj1 = caseService.findCaseByCuidAndUuid(caseDTO.getCuid(), caseDTO.getUuid());
			
			if(caseObj1 != null && !caseObj1.isDeleted()) {
				if(caseDTO.getCaseId() != null) {
					caseObj1.setCaseId(caseDTO.getCaseId());
				}
				
				if(caseDTO.getCaseType() != null) {
					caseObj1.setCaseType(caseDTO.getCaseType());
				}
				
				if(caseDTO.getCaseYear() != null) {
					caseObj1.setCaseYear(caseDTO.getCaseYear());
				}
				
				if(caseDTO.getCaseTitle() != null) {
					caseObj1.setCaseTitle(caseDTO.getCaseTitle());
				}
				
				if(caseDTO.getForumId() != null) {
					caseObj1.setForumId(caseDTO.getForumId());
				}
				
				if(caseDTO.getCaseStatus() != null) {
					caseObj1.setCaseStatus(caseDTO.getCaseStatus());
				}
				
				if(caseDTO.getClient() != null) {
					caseObj1.setClient(caseDTO.getClient());
				}
				
				if(caseDTO.getRespondant() != null) {
					caseObj1.setRespondant(caseDTO.getRespondant());
				}
				
				if(caseDTO.getCategory() != null) {
					caseObj1.setCategory(caseDTO.getCategory());
				}
				
				if(caseDTO.getUuid() != null) {
					caseObj1.setUuid(caseDTO.getUuid());
				}
//				caseObj1.setUpdatedAt(Utils.getTimestamp());
				if(caseService.updateCase(caseObj1) != null) {
					logger.info("Update Case Successfully !!!");
					response.setStatusCode("200");
					response.setMessage("Update Case Successfully !!!");
					response.setData("Update Case Successfully !!!");
				}else {
					logger.info("unable to Update !!!");
					response.setStatusCode("200");
					response.setMessage("unable to Update !!!");
					response.setData(null);
				}
				
			}else {
				logger.error("Case not found !!!");
				response.setStatusCode("200");
				response.setMessage("Case not found !!!");
				response.setData(null);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		
		return response.toJson();	
		
	}
	
	@PostMapping("/deleteByCuidAndUuid")
	public String deleteCase(@RequestBody CaseDTO caseDTO){
		logger.info("POST /deleteCase");
		
		if(caseDTO.getCuid() != null && caseDTO.getUuid() != null) {
			CaseDAO caseObj1 = caseService.findCaseByCuidAndUuid(caseDTO.getCuid(), caseDTO.getUuid());
			if(caseObj1!=null && caseObj1.isDeleted()==false) {
				caseObj1.setDeleted(true);
				if(caseService.saveCase(caseObj1)!=null) {
					
					logger.info("Deleted Successfully !!!");
					response.setStatusCode("200");
					response.setMessage("Deleted Successfully !!!");
					response.setData("Deleted Successfully !!!");
				}else {
					
					logger.error("Unable to delete !!!");
					response.setStatusCode("200");
					response.setMessage("Unable to delete !!!");
					response.setData(null);
				}
			}else {
				
				logger.error("Case not Found !!!");
				response.setStatusCode("200");
				response.setMessage("Case not Found !!!");
				response.setData(null);
			}
		}else {
			
			logger.error("Id not Found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not Found !!!");
			response.setData(null);
		}
		
		return response.toJson();
	}
	
	//get all cases of a particular lawyer
	@PostMapping("/getAllByUuid")
	public String getAllByUuid(@RequestBody CaseDTO caseDTO){
		logger.info("POST /getAllByUuid");
		logger.info(caseDTO.getUuid());
		if(caseDTO.getUuid() != null) {
			List<CaseDAO> cases = caseService.findCasesByUuid(caseDTO.getUuid());
			
			List<CaseDAO> caseObj1 = new ArrayList<>();
			
			
			
			if(cases != null && cases.size() != 0){
				for(CaseDAO item: cases) {
					if(!item.isDeleted()) {
						caseObj1.add(item);
					}
				}
				logger.info(caseObj1.size() + "");
				logger.info("Cases Found !!!");
				response.setStatusCode("200");
				response.setMessage("Cases Found !!!");
				response.setData(caseObj1);
			}else {
				logger.error("No Case Found !!!");
				response.setStatusCode("200");
				response.setMessage("No Case Found !!!");
				response.setData(caseObj1);
			}
		}else {
			logger.error("Id key not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id key not found !!!");
			response.setData(null);
		}
		
		return response.toJson();
		
		
	}
			
  //get all cases of a particular lawyer and a particular category
	@PostMapping("/getByUuidAndCategory")
	public String getCasesByUuidAndCategory(@RequestBody CaseDTO caseDTO){
		logger.info("POST /getByUuidAndCategory");
		if(caseDTO.getUuid() != null && caseDTO.getCategory() != null) {
			List<CaseDAO> cases=caseService.findCasesByUuidAndCategory(caseDTO.getUuid(), caseDTO.getCategory());
			
			List<CaseDAO> caseObj1 = new ArrayList<>();
			for(CaseDAO item: cases) {
				if(!item.isDeleted()) {
					caseObj1.add(item);
				}
			}
			
			if(caseObj1.size() != 0) {
				logger.info("Cases Found !!!");
				response.setStatusCode("200");
				response.setMessage("Cases Found !!!");
				response.setData(caseObj1);
			}else {
				logger.info("NO case found !!!");
				response.setStatusCode("200");
				response.setMessage("NO case found !!!");
				response.setData(null);
			}
		}else {
			logger.error("Id key not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id key not found !!!");
			response.setData(null);
		}
		
		return response.toJson();
		
	}
	
	
}