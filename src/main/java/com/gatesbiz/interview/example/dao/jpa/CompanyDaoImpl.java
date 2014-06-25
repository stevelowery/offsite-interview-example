package com.gatesbiz.interview.example.dao.jpa;

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
		return entityManager.createNamedQuery("Company.findByName", Company.class).setParameter("name", name).getSingleResult();
	}
}
