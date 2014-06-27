package com.bluthco.dao.jpa;

import static com.bluthco.model.Companies.BANANA_STAND;
import static com.bluthco.model.Companies.BLUTH_COMPANY;
import static com.bluthco.model.Employees.BUSTER;
import static com.bluthco.model.Employees.GEORGE;
import static com.bluthco.model.Employees.GEORGE_MICHAEL;
import static com.bluthco.model.Employees.GOB;
import static com.bluthco.model.Employees.LINDSEY;
import static com.bluthco.model.Employees.LUCILLE;
import static com.bluthco.model.Employees.MAEBY;
import static com.bluthco.model.Employees.MICHAEL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bluthco.model.Employee;
import com.google.inject.persist.Transactional;

public class EmployeeDaoImplTest extends DaoImplTestBase {

	@Before
	@Transactional
	public void setUp(CompanyDaoImpl companyDao, EmployeeDaoImpl employeeDao) {
		companyDao.persist(BLUTH_COMPANY, BANANA_STAND);
		employeeDao.persist(new Employee[] {
				GEORGE, LUCILLE, MICHAEL, GOB, LINDSEY, BUSTER, GEORGE_MICHAEL, MAEBY
		});
	}

	@Test
	public void test(EmployeeDaoImpl employeeDao) {
		Assert.assertEquals(6, employeeDao.findByCompany(BLUTH_COMPANY, 0, 10).size());
		Assert.assertEquals(2, employeeDao.findByCompany(BANANA_STAND, 0, 10).size());
		Assert.assertEquals(2, employeeDao.findByCompany(BLUTH_COMPANY, 0, 2).size());
		Assert.assertEquals(1, employeeDao.findByCompany(BLUTH_COMPANY, 3, 1).size());
	}
}
