package com.lawtendo.cmtool.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawtendo.cmtool.application.DAO.LawyerDAO;


public interface CaseLawyerRepo extends JpaRepository<LawyerDAO,String>{

}
