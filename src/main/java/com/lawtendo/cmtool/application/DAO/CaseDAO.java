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
@Table(name = "case_table")
@Component
public class CaseDAO {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column( updatable = false)
	private String cuid;
	
	@Column
	private String caseId;
	
	@Column
	private String caseType;
	
	@Column
	private String caseYear;
	
	@Column
	private String caseTitle;
	
	@Column
	private String forumId;
	
	@Column
	private String caseStatus;
	
	@Column
	private String client;
	
	@Column
	private String respondant;
	
	@Column
	private String description;

	@Column
	private String category;
	
	@Column
	private String uuid;
	
	@Column
	private boolean isDeleted;
	
	@Column
	private Timestamp createdAt;
	
	@Column
	private Timestamp updatedAt;
	
	
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseYear() {
		return caseYear;
	}

	public void setCaseYear(String caseYear) {
		this.caseYear = caseYear;
	}

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getRespondant() {
		return respondant;
	}

	public void setRespondant(String respondant) {
		this.respondant = respondant;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public boolean isDeleted() {
		return this.isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	@Override
	public String toString() {
		return "CaseDAO [cuid=" + cuid + ", caseId=" + caseId + ", caseType=" + caseType + ", caseYear=" + caseYear
				+ ", caseTitle=" + caseTitle + ", forumId=" + forumId + ", caseStatus=" + caseStatus + ", client="
				+ client + ", respondant=" + respondant + ", description=" + description + ", category=" + category
				+ ", uuid=" + uuid + ", isDeleted=" + isDeleted + "]";
	}

	

	
	

}
