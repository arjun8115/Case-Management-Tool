package com.lawtendo.cmtool.application.DAO;

import org.springframework.stereotype.Component;

import com.google.api.client.util.DateTime;

@Component
public class EventDAO {
	
	private String id;
	
	private DateTime startDate;
	
	private DateTime endDate;
	
	private boolean showByMonth;
	
	private boolean showByWeek;
	
	private boolean showCurrent;
	
	private String monthNo;
	
	private String year;
	
	private String startTime;
	
	private String endTime;
	
	private String description;
	
	private String summary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isShowByMonth() {
		return showByMonth;
	}

	public void setShowByMonth(boolean showByMonth) {
		this.showByMonth = showByMonth;
	}

	public boolean isShowByWeek() {
		return showByWeek;
	}

	public void setShowByWeek(boolean showByWeek) {
		this.showByWeek = showByWeek;
	}

	public boolean isShowCurrent() {
		return showCurrent;
	}

	public void setShowCurrent(boolean showCurrent) {
		this.showCurrent = showCurrent;
	}

	public String getMonthNo() {
		return monthNo;
	}

	public void setMonthNo(String monthNo) {
		this.monthNo = monthNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	

	

}
