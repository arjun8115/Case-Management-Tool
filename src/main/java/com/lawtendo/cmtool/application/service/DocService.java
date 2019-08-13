package com.lawtendo.cmtool.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.DocDAO;
import com.lawtendo.cmtool.application.repository.DocRepo;

@Service
public class DocService {
	
	private static final Logger logger = LoggerFactory.getLogger(CaseService.class);
	
	@Autowired
	DocRepo docRepo;
	
	public DocDAO saveDocs(DocDAO docDAO) {

		DocDAO driveObj1 = docRepo.save(docDAO);
		return driveObj1 != null ? driveObj1 : null;
	}
	
	public List<DocDAO> findDocsByUuidAndCuid(String cuid, String uuid) {
		if(cuid!=null && uuid!=null) {
			logger.info("DriveService->findDocs");
			List<DocDAO> driveObj = docRepo.findByCuidAndUuid(cuid, uuid);
			return driveObj != null ? driveObj : null;
		}
		return null;
	}

	public List<DocDAO> findDocByUuid(String uuid){
		if(uuid!=null) {
			logger.info("DriveService ->findDocsByUuid");
			List<DocDAO> docObj = docRepo.findByUuid(uuid);
			return docObj != null ? docObj : null;
		}
		return null;
	}
}
