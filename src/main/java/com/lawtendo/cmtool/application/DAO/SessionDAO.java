package com.lawtendo.cmtool.application.DAO;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "session_table")
@Component
public class SessionDAO {
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(updatable = false)
	private String sessionId;

	@Column
	private String token;
	
	@Column
	private String role;
	
	@Column
	private String email;
	
	@Column
	private String uuid;
	
	@Column
	private Timestamp createdAt;
	
	@Column
	private Timestamp expiredAt;
	

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", token=" + token + ", role=" + role + ", email=" + email
				+ ", uuid=" + uuid + ", createdAt=" + createdAt + ", expiredAt=" + expiredAt + "]";
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Timestamp expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	

}