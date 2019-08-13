package com.lawtendo.cmtool.application.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.util.DateTime;
import com.lawtendo.cmtool.application.DAO.EventDAO;
import com.lawtendo.cmtool.application.DTO.EventDTO;
import com.lawtendo.cmtool.application.helper.CalendarHelper;
import com.lawtendo.cmtool.application.utils.Response;


@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	Response response;
	
	@GetMapping("/duration")
	public void calendarListDuration(@Valid @RequestBody EventDAO eventDAO) throws IOException, GeneralSecurityException {
	DateTime startDate = eventDAO.getStartDate();
	DateTime endDate = eventDAO.getEndDate();
	Boolean month = eventDAO.isShowByMonth();
	Boolean week = eventDAO.isShowByWeek();
	Boolean current = eventDAO.isShowCurrent();
	String monthNo = eventDAO.getMonthNo();
	String year = eventDAO.getYear();
	
	CalendarHelper.listEventsForDuration(startDate, endDate,month,week,current,monthNo,year);
	}
	
	
	@PostMapping("/create")
	public String calendarCreate(@RequestBody EventDTO eventDTO) throws GeneralSecurityException, IOException {
		
		String startTime =  eventDTO.getStartTime();
		String endTime = eventDTO.getEndTime();
		String summary = eventDTO.getSummary();
		String description = eventDTO.getDescription();
		
		if(startTime!=null && endTime!=null && summary!=null && description!=null) {
			if(CalendarHelper.create(summary,description,startTime,endTime) != null) {
				response.setStatusCode("200");
				response.setMessage("Event Created");
				response.setData("Event Created");
			}else {
				response.setStatusCode("200");
				response.setMessage("Unable to create");
				response.setData(null);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("Details not found");
			response.setData(null);
		}
		
		return response.toJson();
	}

}