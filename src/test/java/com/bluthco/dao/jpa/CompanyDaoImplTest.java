package com.bluthco.dao.jpa;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bluthco.inject.BluthModule;
import com.bluthco.model.Company;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;

@RunWith(JukitoRunner.class)
@UseModules({
		BluthModule.class, CompanyDaoImplTest.class
})
public class CompanyDaoImplTest extends AbstractModule {

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
		requestStaticInjection(CompanyDaoImplTest.class);
	}

	@Before
	@Transactional
	public void setUp(CompanyDaoImpl companyDao, @Named(BLUTH_COMPANY_NAME) Company bluthCompany, @Named(BANANA_STAND_NAME) Company bananaStand) {
		companyDao.persist(bluthCompany, bananaStand);
	}

	@Test
	public void testBluthCompany(CompanyDaoImpl companyDao, @Named(BLUTH_COMPANY_NAME) Company bluthCompany) {
		Company queried = companyDao.findByName(BLUTH_COMPANY_NAME);
		assertEquals(bluthCompany, queried);
	}

	@Test
	public void testBananaStand(CompanyDaoImpl companyDao, @Named(BANANA_STAND_NAME) Company bananaStand) {
		Company queried = companyDao.findByName(BANANA_STAND_NAME);
		assertEquals(bananaStand, queried);
	}

	@Test
	public void testUnknown(CompanyDaoImpl companyDao) {
		Company queried = companyDao.findByName("something else");
		assertNull(queried);
	}
}
