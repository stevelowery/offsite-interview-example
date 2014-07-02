package com.bluthco.biz.impl;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bluthco.biz.BluthService;
import com.bluthco.dao.CompanyDao;
import com.bluthco.dao.EmployeeDao;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.util.Providers;

@RunWith(MockitoJUnitRunner.class)
public class BluthServiceImplTest {

	@Mock
	private CompanyDao companyDao;
	@Mock
	private EmployeeDao employeeDao;

	private BluthService bluthService;

	@Before
	public void setUp() {
		bluthService = new BluthServiceImpl(Providers.of(companyDao), Providers.of(employeeDao));
	}

	@Test
	public void testBananaStand() {
		Company company = Company.from(BANANA_STAND_NAME);
		when(companyDao.findByName(BANANA_STAND_NAME)).thenReturn(company);

		Company bananaStand = bluthService.findBananaStand();
		assertEquals(bananaStand, company);

		verify(companyDao, only()).findByName(BANANA_STAND_NAME);
	}

	@Test
	public void testBluthCompany() {
		Company company = Company.from(BLUTH_COMPANY_NAME);
		when(companyDao.findByName(BLUTH_COMPANY_NAME)).thenReturn(company);

		Company bananaStand = bluthService.findBluthCompany();
		assertEquals(bananaStand, company);

		verify(companyDao, only()).findByName(BLUTH_COMPANY_NAME);
	}

	@Test
	public void testBananaStandEmployees() {
		Company bananaStand = Company.from(BANANA_STAND_NAME);

		List<Employee> expected = Arrays.asList(new Employee[] {
				Employee.from(bananaStand, "Bluth", "George Michael"), Employee.from(bananaStand, "Funke", "Maeby")
		});
		when(companyDao.findByName(BANANA_STAND_NAME)).thenReturn(bananaStand);
		when(employeeDao.findByCompany(bananaStand, 0, 10)).thenReturn(expected);

		List<Employee> employees = bluthService.findBananaStandEmployees();
		assertEquals(2, employees.size());
		assertEquals(expected.get(0), employees.get(0));
		assertEquals(expected.get(1), employees.get(1));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testFindAll() {
		bluthService.findAllEmployees();
	}
}
