package com.lawtendo.cmtool.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawtendo.cmtool.application.DAO.InvoiceNewDAO;

public interface InvoiceNewRepo extends JpaRepository<InvoiceNewDAO, String>{

}
