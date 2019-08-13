package com.lawtendo.cmtool.application.DAO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "forum_table")
@Component
public class ForumDAO {

	@Id
	@Column
	private String fuid;
	
	@Column
	private String forumId;			//unique id for each forum
	
	@Column
	private String forumCategory;     //"supreme court","high court"
	
	@Column
	private String forumDir;			//"sc","dhc","bhc"
	
	@Column
	private String forumGroup;			//"Supreme Court","Delhi High Court","Bombay High Court"
	
	@Column
	private String fullName;			//"Bombay High Court at Nagpur","Bombay High Court at Aurangabad"
	
	@Column
	private String shortName;			//"BOM HC-NGP","BOM HC-AWB"
	
	@Column
	private boolean isDeleted;
	
	@Column
	private Timestamp updatedAt;		
	
	@Column
	private Timestamp createdAt;
	
	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getForumCategory() {
		return forumCategory;
	}

	public void setForumCategory(String forumCategory) {
		this.forumCategory = forumCategory;
	}

	public String getForumDir() {
		return forumDir;
	}

	public void setForumDir(String forumDir) {
		this.forumDir = forumDir;
	}

	public String getForumGroup() {
		return forumGroup;
	}

	public void setForumGroup(String forumGroup) {
		this.forumGroup = forumGroup;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
