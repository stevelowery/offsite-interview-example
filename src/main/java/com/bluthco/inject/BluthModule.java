package com.bluthco.inject;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class BluthModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("example"));
	}

	// @Provides
	// @Singleton
	// protected EntityManagerFactory provideEntityManagerFactory() {
	// return Persistence.createEntityManagerFactory("example");
	// }
	//
	// @Provides
	// protected EntityManager provideEntityManager(EntityManagerFactory factory) {
	// return factory.createEntityManager();
	// }
}
