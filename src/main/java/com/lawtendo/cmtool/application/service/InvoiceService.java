package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.InvoiceDAO;
import com.lawtendo.cmtool.application.repository.InvoiceRepo;
import com.lawtendo.cmtool.application.utils.Utils;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepo invoiceRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
	
	public InvoiceDAO save(InvoiceDAO invoiceObj) {
		logger.info("InvoiceService -> save()");
		
		Timestamp timestamp = Utils.getTimestamp();
		invoiceObj.setUpdatedAt(timestamp);
		invoiceObj.setCreatedAt(timestamp);
		return invoiceRepo.save(invoiceObj);
		
	}
	
	public InvoiceDAO updateInvoice(InvoiceDAO invoiceObj) {
		logger.info("InvoiceService -> updateInvoice()");
		
		Timestamp timestamp = Utils.getTimestamp();
		invoiceObj.setUpdatedAt(timestamp);
		
		InvoiceDAO invoiceDAO = invoiceRepo.save(invoiceObj);
		return invoiceDAO != null ? invoiceDAO : null;
	}
	
	
	public List<InvoiceDAO> findallByUuid(String uuid){
		logger.info("InvoiceService -> getlistBy uuid");
		return invoiceRepo.findByUuid(uuid);
	}
	
	public List<InvoiceDAO> findallBycuidanduuid(String cuid,String uuid){
		logger.info("InvoiceService -> getlistBy cuid and uuid");
		return invoiceRepo.findByCuidAndUuid(cuid, uuid);
	}
	
	public Long deleteInvoiceByCuidAndUuidAndIuid(String cuid, String uuid, String iuid) {
		if(cuid != null && uuid != null && iuid!= null) {
			logger.info("InvoiceService -> deleteInvoiceByCuidAndUuidAndIuid");
			return invoiceRepo.deleteByCuidAndUuidAndIuid(cuid, uuid, iuid);
		}
		return null;
	}
	
	public InvoiceDAO findinvoiceByIuidAndCuidAndUuid(String iuid, String cuid, String uuid) {
		if(iuid!=null && cuid!=null && uuid!=null) {
			logger.info("InvoiceService -> find invoice by iuid, cuid, uuid");
			return invoiceRepo.findByIuidAndCuidAndUuid(iuid, cuid, uuid);
		}
		return null;
	}
	

}
