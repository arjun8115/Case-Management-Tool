package com.lawtendo.cmtool.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.lawtendo.cmtool.application.DAO.UserDAO;

public interface UserRepo extends CrudRepository<UserDAO, String> {

	UserDAO findByEmail(String email);
	
	UserDAO findByUuid(String uuid);
	
	UserDAO findByEmailAndAuth(String email, String auth);

}
