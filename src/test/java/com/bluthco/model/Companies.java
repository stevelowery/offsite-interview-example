package com.bluthco.model;

import static com.bluthco.biz.BluthService.BANANA_STAND_NAME;
import static com.bluthco.biz.BluthService.BLUTH_COMPANY_NAME;

public final class Companies {

	public static final Company BLUTH_COMPANY = Company.from(BLUTH_COMPANY_NAME);
	public static final Company BANANA_STAND = Company.from(BANANA_STAND_NAME);
}
