package com.bluthco.dao.jpa;

import static com.bluthco.model.Employee.QUERY_FIND_BY_COMPANY;

import java.util.List;

import javax.persistence.EntityManager;

import com.bluthco.dao.EmployeeDao;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
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
	public List<Employee> findByCompany(Company company, int first, int limit) {
		if (company == null) {
			throw new IllegalArgumentException("Company argument cannot be null");
		}
		return entityManager.createNamedQuery(QUERY_FIND_BY_COMPANY, Employee.class).setParameter("company", company).setFirstResult(first).setMaxResults(limit).getResultList();
	}
}
