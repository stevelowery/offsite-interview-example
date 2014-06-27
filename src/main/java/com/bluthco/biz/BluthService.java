package com.bluthco.biz;

import java.util.List;

import com.bluthco.biz.impl.BluthServiceImpl;
import com.bluthco.model.Company;
import com.bluthco.model.Employee;
import com.google.inject.ImplementedBy;

@ImplementedBy(BluthServiceImpl.class)
public interface BluthService {

	String BLUTH_COMPANY_NAME = "The Bluth Company";
	String BANANA_STAND_NAME = "Bluth's Frozen Banana Stand";

	Company findBluthCompany();

	Company findBananaStand();

	List<Employee> findBananaStandEmployees();

	List<Employee> findAllEmployees();
}
