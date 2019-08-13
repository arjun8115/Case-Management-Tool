package com.lawtendo.cmtool.application.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawtendo.cmtool.application.DAO.CaseDAO;
import com.lawtendo.cmtool.application.helper.DriveHelper;
import com.lawtendo.cmtool.application.service.CaseService;
import com.lawtendo.cmtool.application.utils.Response;
import com.lawtendo.cmtool.application.DAO.DocDAO;
import com.lawtendo.cmtool.application.DTO.DocDTO;
import com.lawtendo.cmtool.application.service.DocService;
import com.lawtendo.cmtool.application.utils.Utils;

@RestController
@RequestMapping("/doc")
public class DocController {
	
	@Autowired
	CaseService caseService;
	
	@Autowired
	DocService docService;
	
//	@Autowired
//	DocDAO docDAO;
	
	@Autowired
	Response response;
	
	@Autowired
	DocDTO docDTO;
	
	private static final Logger logger = LoggerFactory.getLogger(DocController.class);

	
	@GetMapping("/get")
	public void getDrive() throws GeneralSecurityException, IOException {
		DriveHelper.driveAPI();
	}
	
	@PostMapping("/create")
	public String fileupload(@RequestBody DocDTO docDTO) throws IOException, GeneralSecurityException {
		logger.info("doc->controller");
		if(docDTO.getCuid() != null && docDTO.getUuid() != null && docDTO.getFileId() != null) {
			logger.info("Case Id and Uuid found");
			
			CaseDAO caseDAO = caseService.findCaseByCuidAndUuid(docDTO.getCuid(), docDTO.getUuid());
			if(caseDAO != null) {
				logger.info("case found ");
				String fileId = docDTO.getFileId();
				if(fileId != null) {
					
					String url = "https://drive.google.com/file/d/"+fileId+"/view";
					String fileName = docDTO.getFileName();
					String mimeType = docDTO.getMimeType();
					
					DocDAO docDAO = new DocDAO();
					docDAO.setUuid(docDTO.getUuid());
					docDAO.setCuid(docDTO.getCuid());
					docDAO.setFileName(fileName);
					docDAO.setMimeType(mimeType);
					docDAO.setFileUrl(url);
					Timestamp timestamp = Utils.getTimestamp();
					docDAO.setCreatedAt(timestamp);
					
					DocDAO docObj = docService.saveDocs(docDAO);
					
					if(docObj !=null) {
						response.setStatusCode("200");
						response.setMessage("File Uploaded !!!");
						response.setData(docObj);
					}else {
						response.setStatusCode("200");
						response.setMessage("Unable to save !!!");
						response.setData(null);
					}
				}else {
					response.setStatusCode("200");
					response.setMessage("Unable to upload !!!");
					response.setData(null);
				}
			}else {
				response.setStatusCode("200");
				response.setMessage("Case Not Found !!!");
				response.setData(null);
			}
		}else {
			response.setStatusCode("200");
			response.setMessage("Ids not found");
			response.setData(null);
		}
		
		return response.toJson();
		
	}
	
	@PostMapping("/getByUuidAndCuid")
	public String fileGet(@RequestBody DocDTO docDTO) {
		logger.info("GET docs by cuid and uuid");
		if(docDTO.getCuid()!=null && docDTO.getUuid()!=null) {
			List<DocDAO> driveObj = docService.findDocsByUuidAndCuid(docDTO.getCuid(), docDTO.getUuid());
			if(driveObj!=null) {
				logger.info("Docs Found Successfully !!!");
				response.setStatusCode("200");
				response.setMessage("Docs Found Successfully !!!");
				response.setData(driveObj);
			}
			else {
				logger.error("No Docs Found !!!");
				response.setStatusCode("200");
				response.setMessage("No Docs Found !!!");
				List<Object> list = null;
				response.setData(list);
			}
		}else {
			logger.error("Id not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}

	@PostMapping("/getAllByUuid")
	public String getDocByUuid(@RequestBody DocDTO docDTO) {
		logger.info("POST-> getByCuid");
		if(docDTO.getUuid()!=null) {
			List<DocDAO> docObj = docService.findDocByUuid(docDTO.getUuid());
			if(docObj!=null) {
				logger.info("Document Found !!!");
				response.setStatusCode("200");
				response.setMessage("Document Found !!!");
				response.setData(docObj);
			}else {
				logger.info("NO document found !!!");
				response.setStatusCode("200");
				response.setMessage("NO document found !!!");
				response.setData(null);
			}
		}else {
			logger.error("Id key not found !!!");
			response.setStatusCode("200");
			response.setMessage("Id key not found !!!");
			response.setData(null);
		}
		return response.toJson();
	}
}
