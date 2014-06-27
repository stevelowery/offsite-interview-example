package com.bluthco.biz.impl;

import java.util.List;

import com.bluthco.biz.BluthService;
import com.bluthco.dao.CompanyDao;
import com.bluthco.dao.EmployeeDao;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BluthServiceImpl implements BluthService {

	private Provider<CompanyDao> companyDaoProvider;
	private Provider<EmployeeDao> employeeDaoProvider;

	@Inject
	public BluthServiceImpl(Provider<CompanyDao> companyDaoProvider, Provider<EmployeeDao> employeeDaoProvider) {
		super();
		this.companyDaoProvider = companyDaoProvider;
		this.employeeDaoProvider = employeeDaoProvider;
	}

	@Override
	public Company findBluthCompany() {
		return companyDaoProvider.get().findByName(BLUTH_COMPANY_NAME);
	}

	@Override
	public Company findBananaStand() {
		return companyDaoProvider.get().findByName(BANANA_STAND_NAME);
	}

	@Override
	public List<Employee> findBananaStandEmployees() {
		return employeeDaoProvider.get().findByCompany(findBananaStand(), 0, 10);
	}

	@Override
	public List<Employee> findAllEmployees() {
		throw new UnsupportedOperationException();
	}
}
