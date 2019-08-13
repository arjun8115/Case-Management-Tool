package com.lawtendo.cmtool.application.validations;

import java.util.ArrayList;
import java.util.List;

import com.lawtendo.cmtool.application.DTO.CaseDTO;
import com.lawtendo.cmtool.application.DTO.InvoiceDTO;

public interface InvoiceValidator {
	
public static List<String> isValid(InvoiceDTO invoiceDTO){
		
		List<String> validations = new ArrayList<>();
		
		if(!validateUuid(invoiceDTO.getUuid())) {
			validations.add("Invalid uuid !!!");
		}
		
		if(!validateCuid(invoiceDTO.getCuid())) {
			validations.add("Invalid cuid !!!");
		}
		
		if(!validateName(invoiceDTO.getName())) {
			validations.add("Invalid name !!!");
		}
		
		if(!validateStatus(invoiceDTO.getStatus())) {
			validations.add("Invalid status !!!");
		}
		
		if(!validateDate(invoiceDTO.getDate())) {
			validations.add("Invalid date !!!");
		}
		
//		if(!validateDescription(invoiceDTO.getDescription())) {
//			validations.add("Invalid description !!!");
//		}
		
		if(!validateType(invoiceDTO.getType())) {
			validations.add("Invalid invoiceType !!!");
		}
		
		if(!validateAmount(invoiceDTO.getAmount())) {
			validations.add("Invalid amount !!!");
		}
		
		return validations;
		
	}

	public static boolean validateUuid(String uuid) {
		if( uuid != null && uuid.length() > 0) {
			return true;
		}
		return false;
		
	}
	
	public static boolean validateCuid(String cuid) {
		if(cuid != null && cuid.length() > 0) {
			return true;
		}
		return false;
		
	}
	

public static boolean validateName(String name) {
		
		if(name != null && name.length() > 0) {
			return true;
		}
		return false;
	}

public static boolean validateStatus(String status) {
	
	if(status != null && status.length() > 0) {
		return true;
	}
	return false;
}
public static boolean validateDate(String Date) {
	
	if(Date != null && Date.length() > 0) {
		return true;
	}
	return false;
}

public static boolean validateDescription(String description) {
	
	if( description != null && description.length() > 0) {
		return true;
	}
	return false;
}
public static boolean validateType(String type) {
	
	if( type != null && type.length() > 0) {
		return true;
	}
	return false;
}
public static boolean validateAmount(Integer amount) {
	
	if(amount != null) {
		return true;
	}
	return false;
}
}
