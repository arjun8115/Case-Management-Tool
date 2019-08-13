package com.lawtendo.cmtool.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.helper.HTMLToPDFHelper;

@RestController
@RequestMapping("/htmlToPdf")
public class HTMLToPDFController {

	private static final Logger logger = LoggerFactory.getLogger(HTMLToPDFController.class);
	
	@Autowired
	HTMLToPDFHelper hTMLToPDFHelper;
	
	@PostMapping("/convert")
	public void HtmlToPdfConvert() {
		logger.info("convert controller");
		hTMLToPDFHelper.HtmlToPDFHelper();
	}
}
