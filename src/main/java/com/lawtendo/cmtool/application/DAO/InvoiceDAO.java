package com.lawtendo.cmtool.application.DAO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="invoice_table")
@Component
public class InvoiceDAO {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(updatable = false)
	private String iuid;
	
	@Column(updatable = false)
	private String cuid;
	@Column(updatable = false)
	private String uuid;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String type;
	@Column
	private Integer amount;
	@Column
	private Timestamp createdAt;
	@Column
	private Timestamp updatedAt;
	@Column
	private String date;
	@Column
	private String status;
	@Column
	private boolean isDeleted;
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
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
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
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "InvoiceDAO [iuid=" + iuid + ", cuid=" + cuid + ", uuid=" + uuid + ", name=" + name + ", description="
				+ description + ", type=" + type + ", amount=" + amount + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", date=" + date + ", status=" + status + ", isDeleted=" + isDeleted + "]";
	}
	
	

	
	
	
	
	

}
