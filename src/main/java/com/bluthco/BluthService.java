package com.bluthco;

import java.util.List;

import com.bluthco.model.Company;
import com.bluthco.model.Employee;

public interface BluthService {

	Company findBluthCompany();

	Company findBananaStand();

	List<Employee> findBananaStandEmployees();

	List<Employee> findAllEmployees();
}
