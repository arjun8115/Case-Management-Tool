package com.lawtendo.cmtool.application.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lawtendo.cmtool.application.DAO.SessionDAO;

public interface SessionRepo extends CrudRepository<SessionDAO, String> {

	SessionDAO findByToken(String token);
	
	SessionDAO findByUuidAndToken(String uuid, String Token);
	
	@Transactional
	Long deleteByToken(String token);
}
