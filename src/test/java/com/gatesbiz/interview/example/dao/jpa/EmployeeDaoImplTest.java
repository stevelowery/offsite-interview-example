package com.gatesbiz.interview.example.dao.jpa;

import java.util.List;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.AfterClass;
import org.junit.Assert;
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

@RunWith(JukitoRunner.class)
@UseModules(EmployeeDaoImplTest.class)
public class EmployeeDaoImplTest extends AbstractModule {

	private static final String THE_BLUTH_COMPANY = "The Bluth Company";

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
		bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
		bind(CompanyDao.class).to(CompanyDaoImpl.class);
	}

	@Before
	public void setUp(EmployeeDao employeeDao, CompanyDao companyDao) {

		Company company = companyDao.findByName(THE_BLUTH_COMPANY);
		if (company == null) {
			company = Company.from(THE_BLUTH_COMPANY);
			companyDao.persist(company);

			Employee[] employees = new Employee[] {
					Employee.from(company, "Michael", "Bluth"),
					Employee.from(company, "Gob", "Bluth"),
					Employee.from(company, "Lindsey", "Bluth"),
					Employee.from(company, "Buster", "Bluth"),
					Employee.from(company, "George Michael", "Bluth"),
					Employee.from(company, "George Sr", "Bluth"),
					Employee.from(company, "Lucille", "Bluth")
			};
			employeeDao.persist(employees);
		}
	}

	@Test
	public void testQuery(CompanyDao companyDao, EmployeeDao employeeDao) {
		Company company = companyDao.findByName(THE_BLUTH_COMPANY);
		if (company != null) {
			List<Employee> employees = employeeDao.findByCompany(company, 1, 3);
			Assert.assertEquals(3, employees.size());
		}
	}
}
