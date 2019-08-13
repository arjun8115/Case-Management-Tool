package com.lawtendo.cmtool.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawtendo.cmtool.application.DAO.InvoiceDAO;
import com.lawtendo.cmtool.application.DTO.InvoiceDTO;
import com.lawtendo.cmtool.application.service.InvoiceService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.validations.InvoiceValidator;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private Response response;
	
//	@Autowired
//	InvoiceDAO invoiceDAO;

	@PostMapping("/create")
	public String createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		
		logger.info("POST/add");
		List <String> validations = InvoiceValidator.isValid(invoiceDTO);
		
		if(validations.size() == 0) {
			logger.info("invoice validation success");
			InvoiceDAO invoiceDAO = new InvoiceDAO();
			invoiceDAO.setCuid(invoiceDTO.getCuid());
			invoiceDAO.setUuid(invoiceDTO.getUuid());
			invoiceDAO.setAmount(invoiceDTO.getAmount());
			invoiceDAO.setDate(invoiceDTO.getDate());
			invoiceDAO.setDescription(invoiceDTO.getDescription());
			invoiceDAO.setName(invoiceDTO.getName());
			invoiceDAO.setStatus(invoiceDTO.getStatus());
			invoiceDAO.setType(invoiceDTO.getType());
			
			
			if(invoiceService.save(invoiceDAO)!=null) {
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
		}else {
			logger.error("Invalid request Obj !!!");
			response.setStatusCode("200");
			response.setMessage("Invalid request Obj !!!");
			response.setData(null);
		}
		
		return response.toJson();
	}
	
	@PostMapping("/getAllByUuid")
	public String getAllInvoiceAllByUuid(@RequestBody InvoiceDTO invoiceDTO) {
		logger.info("POST/ invoiceGetAllByUuid");
		
		if(invoiceDTO.getUuid()!= null) {
			List<InvoiceDAO> invoiceObj = invoiceService.findallByUuid(invoiceDTO.getUuid());
			
			List<InvoiceDAO> invoiceObj1 = new ArrayList<>();
			
			
			if(invoiceObj != null && invoiceObj.size() != 0) {
				for(InvoiceDAO item: invoiceObj) {
					if(!item.isDeleted()) {
						invoiceObj1.add(item);
					}
				}
				
				logger.info("Invoice Found Successfully !!!");
				response.setStatusCode("200");
				response.setMessage("Invoice Found Successfully !!!");
				response.setData(invoiceObj1);
			}else {
				logger.error("No Invoice Found !!!");
				response.setStatusCode("200");
				response.setMessage("No Invoice Found !!!");
				response.setData(invoiceObj1);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}
	
	@PostMapping("/getByUuidAndCuid")
	public String getInvoiceByUuidAndCuid(@RequestBody InvoiceDTO invoiceDTO) {
		logger.info("POST/ getInvoiceByUuidAndCuid");
		
		if(invoiceDTO.getUuid()!= null && invoiceDTO.getCuid()!= null) {
		
			List<InvoiceDAO> invoiceObj = invoiceService.findallBycuidanduuid(invoiceDTO.getCuid(), invoiceDTO.getUuid());
			logger.info(invoiceObj.toString());
			
			List<InvoiceDAO> invoiceObj1 = new ArrayList<>();
			
			
			if(invoiceObj!= null && invoiceObj.size()!= 0) {
				for(InvoiceDAO item: invoiceObj) {
					if(!item.isDeleted()) {
						invoiceObj1.add(item);
					}
				}
				logger.info("Invoice Found Successfully !!!");
				response.setStatusCode("200");
				response.setMessage("Invoice Found Successfully !!!");
				response.setData(invoiceObj1);
			}else {
				logger.error("No Invoice Found !!!");
				response.setStatusCode("200");
				response.setMessage("No Invoice Found !!!");
				response.setData(invoiceObj1);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}
	
	@PostMapping("/deleteByIuidAndCuidAndUuid")
	public String deleteInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		logger.info("POST/ delete invoice");
		
		if(invoiceDTO.getIuid()!=null && invoiceDTO.getCuid()!=null && invoiceDTO.getUuid()!=null) {
			InvoiceDAO invoiceDAO1 = invoiceService.findinvoiceByIuidAndCuidAndUuid(
								invoiceDTO.getIuid(), invoiceDTO.getCuid(), invoiceDTO.getUuid());
			if(invoiceDAO1 !=null) {
				invoiceDAO1.setDeleted(true);
				if(invoiceService.updateInvoice(invoiceDAO1) != null) {
					logger.info("Invoice deleted Successfully !!!");
					response.setStatusCode("200");
					response.setMessage("Deleted Successfully !!!");
					response.setData("Deleted Successfully !!!");
				}else {
					logger.error("Unable to delete !!!");
					response.setStatusCode("200");
					response.setMessage("Unable to delete !!!");
					response.setData(null);
				}
			}else {
				logger.error("Invoice not Found !!!");
				response.setStatusCode("200");
				response.setMessage("Invoice not Found !!!");
				response.setData(null);
			}
		}else {
			logger.error("Id not Found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not Found !!!");
			response.setData(null);
		}
		return response.toJson();
	}

	@PostMapping("/updateByIuidAndCuidAndUuid")
	public String updateInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		logger.info("Post ->invoice update");
		if(invoiceDTO.getIuid()!=null && invoiceDTO.getCuid()!=null && invoiceDTO.getUuid()!=null) {
			InvoiceDAO invoiceObj = invoiceService.findinvoiceByIuidAndCuidAndUuid(invoiceDTO.getIuid(), invoiceDTO.getCuid(), invoiceDTO.getUuid());
			if(invoiceObj!=null) {
				if(invoiceDTO.getAmount()!=null) {
					invoiceObj.setAmount(invoiceDTO.getAmount());
				}
				if(invoiceDTO.getDate()!=null) {
					invoiceObj.setDate(invoiceDTO.getDate());
				}
				if(invoiceDTO.getDescription()!=null) {
					invoiceObj.setDescription(invoiceDTO.getDescription());
				}
				if(invoiceDTO.getName()!=null) {
					invoiceObj.setName(invoiceDTO.getName());
				}
				if(invoiceDTO.getStatus()!=null) {
					invoiceObj.setStatus(invoiceDTO.getStatus());
				}
				if(invoiceDTO.getType()!=null) {
					invoiceObj.setType(invoiceDTO.getType());
				}
				
				if(invoiceService.updateInvoice(invoiceObj)!=null) {
					logger.info("Update Invoice Successfully !!!");
					response.setStatusCode("200");
					response.setMessage("Update Invoice Successfully !!!");
					response.setData("Update Invoice Successfully !!!");
				}else {
					logger.info("unable to Update !!!");
					response.setStatusCode("200");
					response.setMessage("unable to Update !!!");
					response.setData(null);
				}
			}else {
				logger.error("Invoice not found !!!");
				response.setStatusCode("200");
				response.setMessage("Invoice not found !!!");
				response.setData(null);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}

}
