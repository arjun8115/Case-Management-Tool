package com.lawtendo.cmtool.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawtendo.cmtool.application.DAO.DriveDAO;

public interface DriveRepo extends JpaRepository<DriveDAO, String>{

	List<DriveDAO> findByCuidAndUuid(String cuid, String uuid);
}
