package com.gatesbiz.interview.example.dao;

import java.util.List;

import com.gatesbiz.interview.example.dao.jpa.EmployeeDaoImpl;
import com.gatesbiz.interview.example.model.Company;
import com.gatesbiz.interview.example.model.Employee;
import com.google.inject.ImplementedBy;

@ImplementedBy(EmployeeDaoImpl.class)
public interface EmployeeDao {

	void persist(Employee... employees);

	List<Employee> findByCompany(Company company, int first, int count);
}
