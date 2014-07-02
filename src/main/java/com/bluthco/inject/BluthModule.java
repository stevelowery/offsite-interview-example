package com.bluthco.inject;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;

import com.bluthco.dao.CompanyDao;
import com.bluthco.dao.EmployeeDao;
import com.bluthco.dao.jpa.CompanyDaoImpl;
import com.bluthco.dao.jpa.EmployeeDaoImpl;
import com.bluthco.model.Company;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.jpa.JpaPersistModule;

public class BluthModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("example"));
		bind(CompanyDao.class).to(CompanyDaoImpl.class);
		bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
	}

	@Singleton
	@Provides
	@Named(BLUTH_COMPANY_NAME)
	protected Company provideBluthCompany(CompanyDao companyDao) {
		return companyDao.findByName(BLUTH_COMPANY_NAME);
	}

	@Singleton
	@Provides
	@Named(BANANA_STAND_NAME)
	protected Company provideBananaStand(CompanyDao companyDao) {
		return companyDao.findByName(BANANA_STAND_NAME);
	}
}
