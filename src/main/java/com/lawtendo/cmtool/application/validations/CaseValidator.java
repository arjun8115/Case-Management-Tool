package com.lawtendo.cmtool.application.validations;

import java.util.ArrayList;
import java.util.List;

import com.lawtendo.cmtool.application.DTO.CaseDTO;

public interface CaseValidator {
	
	public static List<String> isValid(CaseDTO caseDTO){
		
		List<String> validations = new ArrayList<>();
		
		if(!validateCaseId(caseDTO.getCaseId())) {
			validations.add("Invalid CaseId !!!");
		}
		
		if(!validateCaseType(caseDTO.getCaseType())) {
			validations.add("Invalid CaseType !!!");
		}
		
		if(!validateCaseYear(caseDTO.getCaseYear())) {
			validations.add("Invalid Case year !!!");
		}
		
		if(!validateCaseTitle(caseDTO.getCaseTitle())) {
			validations.add("Invalid Case Title !!!");
		}
		
		if(!validateForumId(caseDTO.getForumId())) {
			validations.add("Invalid Forum Id !!!");
		}
		
		if(!validateCaseStatus(caseDTO.getCaseStatus())) {
			validations.add("Invalid Case Status !!!");
		}
		
		if(!validateClient(caseDTO.getClient())) {
			validations.add("Invalid Client !!!");
		}
		
		if(!validateRespondant(caseDTO.getRespondant())) {
			validations.add("Invalid Respondant !!!");
		}
		
		if(!validateCategory(caseDTO.getCategory())) {
			validations.add("Invalid Category !!!");
		}
		
		if(!validateUuid(caseDTO.getUuid())) {
			validations.add("Invalid Uuid!!!");
		}
		return validations;
		
	}
	
	public static Boolean validateCaseId(String caseId) {
		if(caseId != null && caseId.length() > 0) {
			return true;
		}
		return false;
		
	}
	
	public static Boolean validateCaseType(String caseType) {
		if(caseType != null && caseType.length() > 0) {
			return true;
		}
		return false;
	}
	
	
	public static Boolean validateCaseYear(String caseYear) {
		if(caseYear != null && caseYear.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateCaseTitle(String caseTitle) {
		if(caseTitle != null && caseTitle.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateForumId(String forumId) {
		if(forumId != null &&forumId.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateCaseStatus(String caseStatus) {
		if(caseStatus != null && caseStatus.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateClient(String client) {
		if(client != null && client.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateRespondant(String respondant) {
		if(respondant != null && respondant.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateCategory(String category) {
		if(category != null && category.length() > 0 ) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateUuid(String uuid) {
		if(uuid != null && uuid.length() > 0) {
			return true;
		}
		return false;
	}

}
