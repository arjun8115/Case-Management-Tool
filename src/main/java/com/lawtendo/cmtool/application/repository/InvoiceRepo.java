package com.lawtendo.cmtool.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lawtendo.cmtool.application.DAO.InvoiceDAO;

public interface InvoiceRepo extends JpaRepository<InvoiceDAO,String> {
		
	List<InvoiceDAO> findByCuidAndUuid(String cuid, String uuid);
	
	InvoiceDAO findByIuidAndCuidAndUuid(String iuid, String cuid, String uuid);
	
	List<InvoiceDAO> findByUuid(String uuid);
	
	@Transactional
	long deleteByCuidAndUuidAndIuid(String cuid, String uuid, String iuid);

}
