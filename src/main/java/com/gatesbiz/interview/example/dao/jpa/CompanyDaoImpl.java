package com.gatesbiz.interview.example.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.gatesbiz.interview.example.dao.CompanyDao;
import com.gatesbiz.interview.example.model.Company;
import com.google.inject.Inject;

public class CompanyDaoImpl implements CompanyDao {

	private final EntityManager entityManager;

	@Inject
	public CompanyDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Company company) {
		entityManager.persist(company);
	}

	@Override
	public Company findByName(String name) {
		List<Company> list = entityManager.createNamedQuery("Company.findByName", Company.class).setParameter("name", name).setMaxResults(1).getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
