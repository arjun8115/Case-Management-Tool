package com.lawtendo.cmtool.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawtendo.cmtool.application.DAO.DocDAO;

public interface DocRepo extends JpaRepository<DocDAO, String>{

	List<DocDAO> findByCuidAndUuid(String cuid, String uuid);
	
	List<DocDAO> findByUuid(String uuid);
}
