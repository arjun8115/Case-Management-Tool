package com.lawtendo.cmtool.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lawtendo.cmtool.application.DAO.ContactDAO;

public interface ContactRepo extends CrudRepository<ContactDAO, String> {

	ContactDAO findByConuid(String conuid);
	
	List<ContactDAO> findByUuid(String Uuid);
	
	List<ContactDAO> findByCuidAndUuid(String cuid, String uuid);
	
	long deleteByConuid(String conuid);
	
}
