package com.bluthco.inject;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;

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
	}

	@Singleton
	@Provides
	@Named(BLUTH_COMPANY_NAME)
	protected Company provideBluthCompany() {
		return Company.from(BLUTH_COMPANY_NAME);
	}

	@Singleton
	@Provides
	@Named(BANANA_STAND_NAME)
	protected Company provideBananaStand() {
		return Company.from(BANANA_STAND_NAME);
	}
}
