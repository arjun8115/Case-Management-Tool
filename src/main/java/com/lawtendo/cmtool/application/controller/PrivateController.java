package com.lawtendo.cmtool.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class PrivateController {
	
	@GetMapping("/private")
	public String privateAction() {
		
		return "This is a private call";
	}

}
