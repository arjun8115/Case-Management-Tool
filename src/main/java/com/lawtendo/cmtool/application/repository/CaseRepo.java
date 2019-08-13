package com.lawtendo.cmtool.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lawtendo.cmtool.application.DAO.CaseDAO;

public interface CaseRepo extends CrudRepository<CaseDAO, String>{

	List<CaseDAO> findByUuid(String uuid);
	
	CaseDAO findByCaseId(String caseId);
	
	CaseDAO findByCuid(String cuid);
	
	long deleteByCuidAndUuid(String cuid, String uuid);
	
	CaseDAO findByCuidAndUuid(String cuid, String uuid);
	
	List<CaseDAO> findByUuidAndCategory(String uuid, String category);
}
