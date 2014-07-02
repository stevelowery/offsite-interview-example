package com.bluthco.dao.jpa;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bluthco.inject.BluthModule;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;

@RunWith(JukitoRunner.class)
@UseModules({
		BluthModule.class, EmployeeDaoImplTest.class
})
public class EmployeeDaoImplTest extends AbstractModule {

	@Inject
	private static PersistService persistService;

	@BeforeClass
	public static void setUpClass() {
		persistService.start();
	}

	@AfterClass
	public static void tearDownClass() {
		persistService.stop();
	}

	@Override
	protected void configure() {
		requestStaticInjection(EmployeeDaoImplTest.class);
	}

	@Before
	@Transactional
	public void setUp(EmployeeDaoImpl employeeDao, @Named(BLUTH_COMPANY_NAME) Company bluthCompany, @Named(BANANA_STAND_NAME) Company bananaStand) {
		employeeDao.persist(new Employee[] {
				Employee.from(bluthCompany, "Bluth", "George"),
				Employee.from(bluthCompany, "Bluth", "Lucille"),
				Employee.from(bluthCompany, "Bluth", "Michael"),
				Employee.from(bluthCompany, "Bluth", "Gob"),
				Employee.from(bluthCompany, "Bluth", "Lindsey"),
				Employee.from(bluthCompany, "Bluth", "Buster"),
				Employee.from(bananaStand, "Bluth", "George Michael"),
				Employee.from(bananaStand, "Bluth", "Maeby")
		});
	}

	@Test
	public void testQueries(EmployeeDaoImpl employeeDao, @Named(BLUTH_COMPANY_NAME) Company bluthCompany, @Named(BANANA_STAND_NAME) Company bananaStand) {
		Assert.assertEquals(6, employeeDao.findByCompany(bluthCompany, 0, 10).size());
		Assert.assertEquals(2, employeeDao.findByCompany(bananaStand, 0, 10).size());
		Assert.assertEquals(2, employeeDao.findByCompany(bluthCompany, 0, 2).size());
		Assert.assertEquals(1, employeeDao.findByCompany(bluthCompany, 3, 1).size());
	}
}
