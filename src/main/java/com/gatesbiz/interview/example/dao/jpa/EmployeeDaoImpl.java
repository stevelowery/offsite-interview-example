package com.gatesbiz.interview.example.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.gatesbiz.interview.example.dao.EmployeeDao;
import com.gatesbiz.interview.example.model.Company;
import com.gatesbiz.interview.example.model.Employee;
import com.google.inject.Inject;

public class EmployeeDaoImpl implements EmployeeDao {

	private final EntityManager entityManager;

	@Inject
	public EmployeeDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Employee... employees) {
		if (employees != null) {
			for (Employee employee : employees) {
				entityManager.persist(employee);
			}
		}
	}

	@Override
	public List<Employee> findByCompany(Company company, int first, int count) {
		TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByCompany", Employee.class);
		query.setParameter("company", company);
		return query.setFirstResult(first).setMaxResults(count).getResultList();
	}
}
