package com.lawtendo.cmtool.application.DTO;

import org.springframework.stereotype.Component;

@Component
public class ContactDTO {

	private String conuid;
	
	private String uuid;
	
	private String cuid;
	
	private String name;
	
	private String contactNumber;
	
	private String email;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConuid() {
		return conuid;
	}

	public void setConuid(String conuid) {
		this.conuid = conuid;
	}
	
}
