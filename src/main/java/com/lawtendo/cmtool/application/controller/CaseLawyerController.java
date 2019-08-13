package com.lawtendo.cmtool.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.CaseDAO;
import com.lawtendo.cmtool.application.DAO.LawyerDAO;
import com.lawtendo.cmtool.application.response.LawyerResponse;
import com.lawtendo.cmtool.application.service.CaseLawyerService;




@Controller
@ResponseBody
@RestController
@RequestMapping("/caseLawyerObj")
public class CaseLawyerController {
	@Autowired
	CaseLawyerService caseLawyerService;
	
	@Autowired
	LawyerResponse lawyerResponse;
	
	private static final Logger logger = LoggerFactory.getLogger(CaseLawyerController.class);
	
	@PostMapping("/add")
	public LawyerDAO createUser(@Valid @RequestBody LawyerDAO userObj) {
		logger.info("POST /add");
		return caseLawyerService.save(userObj);
	}
	
	@GetMapping("/get")
	public List<LawyerDAO> getAllUserList(){
		logger.info("GET /list");
		return caseLawyerService.findAll();
	}
	
	@GetMapping("/getById")
	public ResponseEntity<LawyerResponse> getUserById(@Valid @RequestBody LawyerDAO lawyerObj){
		logger.info("GET /id");
		String uuid= lawyerObj.getUuid();
		LawyerDAO user=caseLawyerService.findOne(uuid);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		LawyerDAO temp = user;
		lawyerResponse.setFirstName(temp.getFirstName());
		lawyerResponse.setEmail(temp.getEmail());
		lawyerResponse.setLastName(temp.getLastName());
		lawyerResponse.setUuid(temp.getUuid());
		lawyerResponse.setMobileNo(temp.getMobileNo());
		lawyerResponse.setUserName(temp.getUserName());
		return ResponseEntity.ok().body(lawyerResponse);
	}
	
	@PutMapping("/updateById")
	public ResponseEntity<LawyerDAO> updateUser(@Valid @RequestBody LawyerDAO userDetails){
		
		logger.info("UPDATE /id");
		String uuid= userDetails.getUuid();
		LawyerDAO user=caseLawyerService.findOne(uuid);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		
		user.setUserName(userDetails.getUserName());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setMobileNo(userDetails.getMobileNo());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setSalt(userDetails.getSalt());
		
		LawyerDAO updateUsers=caseLawyerService.save(user);
		return ResponseEntity.ok().body(updateUsers);
	}
	
	
	@DeleteMapping("/delById")
	public ResponseEntity<LawyerDAO> deleteUser(@Valid @RequestBody LawyerDAO lawyerObj){
		logger.info("DELETE /id");
		String uuid= lawyerObj.getUuid();
		LawyerDAO user=caseLawyerService.findOne(uuid);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		caseLawyerService.delete(user);
		
		return ResponseEntity.ok().build();
		
		
	}
	

}
