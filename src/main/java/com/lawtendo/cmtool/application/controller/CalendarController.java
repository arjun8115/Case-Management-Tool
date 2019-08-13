package com.lawtendo.cmtool.application.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.util.DateTime;

import com.lawtendo.cmtool.application.DAO.CaseDAO;
import com.lawtendo.cmtool.application.DAO.EventDAO;
import com.lawtendo.cmtool.application.DTO.EventDTO;
import com.lawtendo.cmtool.application.helper.CalendarHelper;
@RestController
@RequestMapping("/calendar")


public class CalendarController {
	
//	@GetMapping("/list")
//	public void calendarList() throws IOException, GeneralSecurityException {
//		CalendarHelper.listEvents();
//	}
	
	@GetMapping("/duration")
	public void calendarListDuration(@Valid @RequestBody EventDAO calendarObj) throws IOException, GeneralSecurityException {
	DateTime startDate = calendarObj.getStartDate();
	DateTime endDate = calendarObj.getEndDate();
	Boolean month = calendarObj.isShowByMonth();
	Boolean week = calendarObj.isShowByWeek();
	Boolean current = calendarObj.isShowCurrent();
	String monthNo = calendarObj.getMonthNo();
	String year = calendarObj.getYear();
	
	CalendarHelper.listEventsForDuration(startDate, endDate,month,week,current,monthNo,year);
	}
	
	
	@PostMapping("/create")
	public String calendarCreate(@RequestBody EventDTO calendarObj) throws GeneralSecurityException, IOException {
		String startTime =  calendarObj.getStartTime();
		String endTime = calendarObj.getEndTime();
		String summary = calendarObj.getSummary();
		String description = calendarObj.getDescription();
		if(startTime!=null && endTime!=null && summary!=null && description!=null) {
		CalendarHelper.create(summary,description,startTime,endTime);
		return "event created";
		}
		
		return "null";
	}

}