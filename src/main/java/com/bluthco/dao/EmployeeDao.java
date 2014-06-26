package com.bluthco.dao;

import java.util.List;

import com.bluthco.dao.jpa.EmployeeDaoImpl;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.ImplementedBy;

@ImplementedBy(EmployeeDaoImpl.class)
public interface EmployeeDao {

	void persist(Employee... employees);

	List<Employee> findByCompany(Company company, int first, int limit);
}
