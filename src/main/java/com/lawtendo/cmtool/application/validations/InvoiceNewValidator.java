package com.lawtendo.cmtool.application.validations;

import java.util.ArrayList;
import java.util.List;

import com.lawtendo.cmtool.application.DAO.InvoiceNewDAO;

public interface InvoiceNewValidator {

	
	public static List<String> isValid(InvoiceNewDAO invoiceNewDAO){
		
		List<String> validations = new ArrayList<>();
		
		if(!validateUuid(invoiceNewDAO.getUuid())) {
			validations.add("Invalid uuid !!!");
		}
		
		if(!validateCuid(invoiceNewDAO.getCuid())) {
			validations.add("Invalid cuid !!!");
		}
		
		if(!validateName(invoiceNewDAO.getName())) {
			validations.add("Invalid name !!!");
		}
		
		if(!validateStatus(invoiceNewDAO.getStatus())) {
			validations.add("Invalid status !!!");
		}
		
		if(!validateDate(invoiceNewDAO.getDate())) {
			validations.add("Invalid date !!!");
		}
		
		if(!validateDescription(invoiceNewDAO.getDescription())) {
			validations.add("Invalid description !!!");
		}
		
		if(!validateType(invoiceNewDAO.getType())) {
			validations.add("Invalid invoiceType !!!");
		}
		
		if(!validateAmount(invoiceNewDAO.getAmount())) {
			validations.add("Invalid amount !!!");
		}
		
		if(!validateQuantity(invoiceNewDAO.getQuantity())) {
			validations.add("Invalid Quantity !!!");
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
public static boolean validateQuantity(Integer quantity) {
	if(quantity!= null) {
		return true;
	}
	return false;
}
}
