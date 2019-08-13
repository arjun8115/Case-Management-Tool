package com.lawtendo.cmtool.application.DTO;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class InvoiceDTO {

	private String iuid;
	
	private String cuid;
	
	private String uuid;
	
	private String name;
	
	private String description;
	
	private String Type;
	
	private Integer amount;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
	private String Date;
	
	private String status;

	public String getIuid() {
		return iuid;
	}

	public void setIuid(String iuid) {
		this.iuid = iuid;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InvoiceDTO [iuid=" + iuid + ", cuid=" + cuid + ", uuid=" + uuid + ", name=" + name + ", description="
				+ description + ", Type=" + Type + ", amount=" + amount + ", Date=" + Date + ", status=" + status + "]";
	}

	
	
	
	
	
	
	
}
