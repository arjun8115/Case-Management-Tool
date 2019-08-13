package com.lawtendo.cmtool.application.response;

import org.springframework.stereotype.Component;

@Component
public class ClientResponse {
	
	private String cluid;
	
	private String caseClientName;
	
	private String caseClientPhoneNumber;
	
	private String caseClientEmail;

	public String getCluid() {
		return cluid;
	}

	public void setCluid(String cluid) {
		this.cluid = cluid;
	}

	public String getCaseClientName() {
		return caseClientName;
	}

	public void setCaseClientName(String caseClientName) {
		this.caseClientName = caseClientName;
	}

	public String getCaseClientPhoneNumber() {
		return caseClientPhoneNumber;
	}

	public void setCaseClientPhoneNumber(String caseClientPhoneNumber) {
		this.caseClientPhoneNumber = caseClientPhoneNumber;
	}

	public String getCaseClientEmail() {
		return caseClientEmail;
	}

	public void setCaseClientEmail(String caseClientEmail) {
		this.caseClientEmail = caseClientEmail;
	}
	
	

}
