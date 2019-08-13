package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.InvoiceNewDAO;
import com.lawtendo.cmtool.application.repository.InvoiceNewRepo;
import com.lawtendo.cmtool.application.utils.Utils;

@Service
public class InvoiceNewService {

	@Autowired
	InvoiceNewRepo invoiceNewRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceNewService.class);
	
	public InvoiceNewDAO save(InvoiceNewDAO invoiceNewObj) {
		logger.info("InvoiceNewService-> save");
		Timestamp timestamp = Utils.getTimestamp();
		invoiceNewObj.setCreatedAt(timestamp);
		invoiceNewObj.setUpdatedAt(timestamp);
		invoiceNewObj.setCurrency("INR");
		invoiceNewObj.setItem_header("Description");
		invoiceNewObj.setPayment_terms_title("CASE NUMBER");
		invoiceNewObj.setQuantity_header("Quantity");
		invoiceNewObj.setPurchase_order_title("CASE NAME");
		invoiceNewObj.setInvoice_number_title("INVOICE NO. :");
		invoiceNewObj.setLogo("https://invoiced.com/img/logo-invoice.png");
		invoiceNewObj.setNotes("Thanks for your business!");
		
		
		return invoiceNewRepo.save(invoiceNewObj);
	}
	
}
