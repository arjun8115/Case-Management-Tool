package com.lawtendo.cmtool.application.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawtendo.cmtool.application.DAO.DriveDAO;
import com.lawtendo.cmtool.application.repository.DriveRepo;
import com.lawtendo.cmtool.application.utils.Utils;

@Service
public class DriveService {
	
	private static final Logger logger = LoggerFactory.getLogger(CaseService.class);
	
	@Autowired
	DriveRepo driveRepo;
	
	public DriveDAO saveDocs(DriveDAO driveObj) {

		DriveDAO driveObj1 = driveRepo.save(driveObj);
		return driveObj1 != null ? driveObj1 : null;
	}
	
	public List<DriveDAO> findDocsByUuidAndCuid(String cuid, String uuid) {
		if(cuid!=null && uuid!=null) {
			logger.info("DriveService->findDocs");
			List<DriveDAO> driveObj = driveRepo.findByCuidAndUuid(cuid, uuid);
			return driveObj != null ? driveObj : null;
		}
		return null;
	}

}
