package com.lawtendo.cmtool.application.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.lawtendo.cmtool.application.Application;

public class CalendarHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarHelper.class);

	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
//    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
    	logger.info("load credential");
    	File file = ResourceUtils.getFile("classpath:credentials.json");
        InputStream in = new FileInputStream(file);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        logger.info("scopes");
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        logger.info("port");
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    
    
    
	/*
	 * public static void listEvents() throws IOException, GeneralSecurityException
	 * { // Build a new authorized API client service. logger.info("list event");
	 * final NetHttpTransport HTTP_TRANSPORT =
	 * GoogleNetHttpTransport.newTrustedTransport(); Calendar service = new
	 * Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY,
	 * getCredentials(HTTP_TRANSPORT)) .setApplicationName(APPLICATION_NAME)
	 * .build();
	 * 
	 * // List the next 10 events from the primary calendar. DateTime now = new
	 * DateTime(System.currentTimeMillis()); Events events =
	 * service.events().list("primary") .setMaxResults(10) .setTimeMin(now)
	 * .setOrderBy("startTime") .setSingleEvents(true) .execute(); List<Event> items
	 * = events.getItems(); if (items.isEmpty()) {
	 * System.out.println("No upcoming events found."); } else {
	 * System.out.println("Upcoming events"); for (Event event : items) { DateTime
	 * start = event.getStart().getDateTime(); if (start == null) { start =
	 * event.getStart().getDate(); } System.out.printf("%s (%s)\n",
	 * event.getSummary(), start); } }
	 * 
	 * }
	 */
    
    
    public static void listEventsForDuration(DateTime startTime, DateTime endTime,boolean month,boolean week, boolean current,String monthNo,String year) throws IOException, GeneralSecurityException {
    	// Build a new authorized API client service.
    	logger.info("list event for duration");
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        DateTime startDateTime = null;
        DateTime endDateTime = null;
      //  String temp =startTime.toString();
    //   System.out.println(temp);
       int flag=0;
       
      // DateTime sta = new DateTime(temp);
        
        if(startTime!=null&&endTime!=null) {
        	startDateTime = startTime;
        	endDateTime = endTime;
        }else if(month) {
        	logger.info("month");
        	startDateTime = new DateTime(System.currentTimeMillis());
        	endDateTime = new DateTime(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
        }else if(week) {
        	logger.info("week");
        	startDateTime = new DateTime(System.currentTimeMillis());
        	endDateTime = new DateTime(System.currentTimeMillis() + 7*24*60*60*1000);
        }else if(current){
        	logger.info("current");
        	startDateTime = new DateTime(System.currentTimeMillis());
        	endDateTime = new DateTime(System.currentTimeMillis() + 1*24*60*60*1000);
        }
        else if(monthNo!=null&&year!=null){
        	flag=1;
        }else if(monthNo!=null) {
        	flag=2;
        }else {
        	flag=3;
        }
        
        if(flag==0) {
        logger.info("ist");
        // List the events for a duration from the primary calendar.
        Events events = service.events().list("primary")
        		.setTimeZone("INDIA")
        		.setTimeMin(startDateTime)
        		.setTimeMax(endDateTime)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
             
                	 System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
       
    }else {
    	logger.info("second");
    	String pageToken = null;
    	do {
    	  Events events = service.events().list("primary").setPageToken(pageToken).execute();
    	  List<Event> items = events.getItems();
    	  
    	  for (Event event : items) {
              DateTime start = event.getStart().getDateTime();
              if (start == null) {
                  start = event.getStart().getDate();
              }
              String st = start.toString();
                if(flag==1) {
                
                String a=st.substring(0, 4);
                String b=st.substring(5, 7);
                if(a.equals(year)&&b.equals(monthNo)) {
                logger.info("monthNo and year");
              	 System.out.printf("%s (%s)\n", event.getSummary(), start);
                }
                }else if(flag==2) {
                	
                	String a=st.substring(5, 7);
                	if(a.equals(monthNo)) {
                		logger.info("monthno");
                     	 System.out.printf("%s (%s)\n", event.getSummary(), start);
                       }
                }else {
                	
                	String a=st.substring(0, 4);
                	if(a.equals(year)) {
                		logger.info("year");
                     	 System.out.printf("%s (%s)\n", event.getSummary(), start);
                       }
                }
          }
    	  pageToken = events.getNextPageToken();
    	} while (pageToken != null);
    }
        
    }
    
    public static String create(String summary, String description, String startTime, String endTime) throws GeneralSecurityException, IOException {
    	logger.info("create event");
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        Event event = new Event().setSummary(summary).setDescription(description);
        
        DateTime startDateTime = new DateTime(startTime);
        EventDateTime start = new EventDateTime().setDateTime(startDateTime);
        event.setStart(start);
        
        DateTime endDateTime = new DateTime(endTime);
        EventDateTime end = new EventDateTime().setDateTime(endDateTime);
        event.setEnd(end);
        	
        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        
        return event.getHtmlLink() != null && event.getHtmlLink().length() > 0 ? event.getHtmlLink() : null;
        
    }
    
   

}