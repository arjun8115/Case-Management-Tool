package com.lawtendo.cmtool.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.InvoiceNewDAO;
import com.lawtendo.cmtool.application.service.InvoiceNewService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.validations.InvoiceNewValidator;

@RestController
@RequestMapping("/invoiceNew")
public class InvoiceNewController {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceNewController.class);
	
	@Autowired
	private InvoiceNewService invoiceNewService;
	
	@Autowired
	private Response response;
	
	public String createInvoiceNew(@RequestBody InvoiceNewDAO invoiceNewObj1) {
		logger.info("POST/add");
		List <String> validations = InvoiceNewValidator.isValid(invoiceNewObj1);
		
		if(validations.size()==0) {
			logger.info("invoicenew validation success");
			InvoiceNewDAO invoiceNewObj2 = new InvoiceNewDAO();
			invoiceNewObj2.setCuid(invoiceNewObj1.getCuid());
			invoiceNewObj2.setUuid(invoiceNewObj1.getUuid());
			invoiceNewObj2.setAmount(invoiceNewObj1.getAmount());
			invoiceNewObj2.setDate(invoiceNewObj1.getDate());
			invoiceNewObj2.setDescription(invoiceNewObj1.getDescription());
			invoiceNewObj2.setName(invoiceNewObj1.getName());
			invoiceNewObj2.setStatus(invoiceNewObj1.getStatus());
			invoiceNewObj2.setType(invoiceNewObj1.getType());
			invoiceNewObj2.setQuantity(invoiceNewObj1.getQuantity());
			
			if(invoiceNewService.save(invoiceNewObj2)!=null) {
				logger.info("Created Successfully");
				response.setStatusCode("200");
				response.setMessage("Created Successfully ");
				response.setData("Created Successfully");
			}else {
				logger.error("Unable to create invoice");
				response.setStatusCode("200");
				response.setMessage("Unable to create case");
				response.setData(null);
			}
		}
			else {
				logger.error("Invalid request Obj !!!");
				response.setStatusCode("200");
				response.setMessage("Invalid request Obj !!!");
				response.setData(null);
			}
			
		return response.toJson();
	}
}
