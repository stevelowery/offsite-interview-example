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

import com.gatesbiz.interview.example.dao.CompanyDao;
import com.gatesbiz.interview.example.dao.EmployeeDao;
import com.gatesbiz.interview.example.inject.OffsiteInterviewExampleModule;
import com.gatesbiz.interview.example.model.Company;
import com.gatesbiz.interview.example.model.Employee;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;

@RunWith(JukitoRunner.class)
@UseModules({
		OffsiteInterviewExampleModule.class, EmployeeDaoImplTest.class,
})
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
	@Transactional
	public void setUp(EmployeeDao employeeDao, CompanyDao companyDao) {

		Company company = companyDao.findByName(THE_BLUTH_COMPANY);
		if (company == null) {
			company = Company.of(THE_BLUTH_COMPANY);
			companyDao.persist(company);

			Employee[] employees = new Employee[] {
					Employee.of(company, "Michael", "Bluth"),
					Employee.of(company, "Gob", "Bluth"),
					Employee.of(company, "Lindsey", "Bluth"),
					Employee.of(company, "Buster", "Bluth"),
					Employee.of(company, "George Michael", "Bluth"),
					Employee.of(company, "George Sr", "Bluth"),
					Employee.of(company, "Lucille", "Bluth")
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
