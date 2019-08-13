package com.lawtendo.cmtool.application.validations;

import java.util.ArrayList;
import java.util.List;

import com.lawtendo.cmtool.application.DTO.UserDTO;

public interface UserValidator {

	//use for signup validation
	public static List<String> isValid(UserDTO userDTO) {
		
		List<String> validations = new ArrayList<String>();
		
		if(!validateEmail(userDTO.getEmail())) {
			validations.add("Invalid Email");
		}
		
		if(!validateFirstName(userDTO.getFirstName())) {
			validations.add("Invalid First Name");
		}
		
		if(!validateLastName(userDTO.getLastName())) {
			validations.add("Invalid Last Name");
		}
		
		if(!validateMobileNo(userDTO.getMobileNo())) {
			validations.add("Invalid Mobile No.");
		}
		
		if(!validatePassword(userDTO.getPassword())) {
			validations.add("Invalid Password");
		}
		
		
		return validations;
	}
	
	public static Boolean validateUuid(String uuid) {
		if(uuid != null && uuid.length() > 0) {
			return true;
		}
		return false;
		
	}
	
	public static Boolean validateFirstName(String firstname) {
		
		if(firstname != null && firstname.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateLastName(String lastName) {
		
		if(lastName != null && lastName.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateEmail(String email) {
		if(email != null && email.length() > 0) {
			return true;
		}
		return false;
		
	}
	
	public static Boolean validateRole(String role) {
		if(role != null && role.length() > 0) {
			return true;
		}
		return false;
	}
	
	
	public static Boolean validatePassword(String password) {
		if(password != null && password.length() > 0) {
			return true;
		}
		return false;
		
	}
	
	public static Boolean validateMobileNo(String mobileNo) {
		if(mobileNo != null && mobileNo.length() > 0 && mobileNo.length() <= 10 ) {
			return true;
		}
		return false;
	}
	
	
}
