package com.bluthco.dao.jpa;

import static com.bluthco.model.Company.QUERY_FIND_BY_NAME;

import java.util.List;

import javax.persistence.EntityManager;

import com.bluthco.dao.CompanyDao;
import com.bluthco.model.Company;
import com.google.inject.Inject;

public class CompanyDaoImpl implements CompanyDao {

	private final EntityManager entityManager;

	@Inject
	public CompanyDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Company... companies) {
		if (companies != null) {
			for (Company company : companies) {
				entityManager.persist(company);
			}
		}
	}

	@Override
	public Company findByName(String name) {
		List<Company> list = entityManager.createNamedQuery(QUERY_FIND_BY_NAME, Company.class).setParameter("name", name).setMaxResults(1).getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
