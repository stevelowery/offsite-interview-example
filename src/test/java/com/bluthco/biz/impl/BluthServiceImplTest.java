package com.bluthco.biz.impl;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bluthco.dao.CompanyDao;
import com.bluthco.dao.EmployeeDao;
import com.bluthco.dao.jpa.CompanyDaoImpl;
import com.bluthco.dao.jpa.EmployeeDaoImpl;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

@RunWith(JukitoRunner.class)
@UseModules(BluthServiceImplTest.class)
public class BluthServiceImplTest extends AbstractModule {

	@Inject
	private static PersistService persistService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		persistService.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		persistService.stop();
	}

	@Before
	@Transactional
	public void setUp(CompanyDao companyDao, EmployeeDao employeeDao) {

		Company bluthCompany = Company.from(BLUTH_COMPANY_NAME);
		Company bananaStand = Company.from(BANANA_STAND_NAME);
		companyDao.persist(bluthCompany, bananaStand);

		Employee[] employees = new Employee[] {
				Employee.from(bluthCompany, "Michael", "Bluth"),
				Employee.from(bluthCompany, "Gob", "Bluth"),
				Employee.from(bluthCompany, "Lindsey", "Bluth"),
				Employee.from(bluthCompany, "Buster", "Bluth"),
				Employee.from(bluthCompany, "Lucille", "Bluth"),
				Employee.from(bluthCompany, "George Sr", "Bluth"),
				Employee.from(bananaStand, "George Michael", "Bluth"),
				Employee.from(bananaStand, "Maeby", "Funke")
		};
		employeeDao.persist(employees);
	}

	@Test
	public void testBananaStand(BluthServiceImpl bluthService) {
		Company bananaStand = bluthService.findBananaStand();
		assertNotNull(bananaStand);
		assertEquals(BANANA_STAND_NAME, bananaStand.getName());
	}

	@Test
	public void testBluthCompany(BluthServiceImpl bluthService) {
		Company bluthCompany = bluthService.findBluthCompany();
		assertNotNull(bluthCompany);
		assertEquals(BLUTH_COMPANY_NAME, bluthCompany.getName());
	}

	@Test
	public void testBananaStandEmployees(BluthServiceImpl bluthService) {
		List<Employee> employees = bluthService.findBananaStandEmployees();
		assertNotNull(employees);
		assertEquals(2, employees.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testFindAll(BluthServiceImpl bluthService) {
		bluthService.findAllEmployees();
	}

	@After
	@Transactional
	public void tearDown(EntityManager entityManager) {
		entityManager.createNativeQuery("truncate table employee").executeUpdate();
		entityManager.createNativeQuery("truncate table company").executeUpdate();
	}

	@Override
	protected void configure() {
		requestStaticInjection(BluthServiceImplTest.class);
		bind(CompanyDao.class).to(CompanyDaoImpl.class);
		bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
		install(new JpaPersistModule("example"));
	}
}
