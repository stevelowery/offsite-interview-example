package com.bluthco.dao;

import com.bluthco.dao.jpa.CompanyDaoImpl;
import com.bluthco.model.Company;
import com.google.inject.ImplementedBy;

@ImplementedBy(CompanyDaoImpl.class)
public interface CompanyDao {

	void persist(Company... companies);

	Company findByName(String name);
}
