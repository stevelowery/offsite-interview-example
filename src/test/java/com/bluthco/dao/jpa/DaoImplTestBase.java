package com.bluthco.dao.jpa;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

@RunWith(JukitoRunner.class)
@UseModules(DaoImplTestBase.class)
public class DaoImplTestBase extends AbstractModule {

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
		requestStaticInjection(DaoImplTestBase.class);
		install(new JpaPersistModule("example"));
	}

}
