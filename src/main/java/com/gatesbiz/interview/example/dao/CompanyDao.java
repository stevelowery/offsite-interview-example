package com.gatesbiz.interview.example.dao;

import com.gatesbiz.interview.example.dao.jpa.CompanyDaoImpl;
import com.gatesbiz.interview.example.model.Company;
import com.google.inject.ImplementedBy;

@ImplementedBy(CompanyDaoImpl.class)
public interface CompanyDao {

	Company findByName(String name);

	void persist(Company company);
}
