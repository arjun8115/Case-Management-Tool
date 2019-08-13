package com.lawtendo.cmtool.application.DTO;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class CaseDTO {

		private String cuid;		
		
		private String caseId;
		
		private String caseType;
		
		private String caseYear;
		
		private String caseTitle;
		
		private String forumId;
		
		private String caseStatus;
		
		private String client;
		
		private String respondant;
		
		private String description;
		
		private String category;
		
		private String uuid;
		
		private Timestamp createdAt;
		
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
			return "Case [cuid=" + cuid + ", caseId=" + caseId + ", caseType=" + caseType + ", caseYear=" + caseYear
					+ ", caseTitle=" + caseTitle + ", forumId=" + forumId + ", caseStatus=" + caseStatus + ", client="
					+ client + ", respondant=" + respondant + ", category=" + category + ", Uuid=" + uuid + "]";
		}

}
