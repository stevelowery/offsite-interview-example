package com.bluthco.dao.jpa;

import static com.bluthco.model.Companies.BANANA_STAND;
import static com.bluthco.model.Companies.BLUTH_COMPANY;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bluthco.model.Companies;
import com.bluthco.model.Company;
import com.google.inject.persist.Transactional;

public class CompanyDaoImplTest extends DaoImplTestBase {

	@Before
	@Transactional
	public void setUp(CompanyDaoImpl companyDao) {
		companyDao.persist(BLUTH_COMPANY, BANANA_STAND);
	}

	@Test
	public void test(CompanyDaoImpl companyDao) {
		Company queried = companyDao.findByName(Companies.BLUTH_COMPANY.getName());
		Assert.assertEquals(Companies.BLUTH_COMPANY, queried);
	}
}
