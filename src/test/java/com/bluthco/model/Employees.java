package com.bluthco.model;

import static com.bluthco.model.Companies.BANANA_STAND;
import static com.bluthco.model.Companies.BLUTH_COMPANY;

public final class Employees {

	public static final Employee GEORGE = Employee.from(BLUTH_COMPANY, "Bluth", "George");
	public static final Employee LUCILLE = Employee.from(BLUTH_COMPANY, "Bluth", "Lucille");
	public static final Employee MICHAEL = Employee.from(BLUTH_COMPANY, "Bluth", "Michael");
	public static final Employee GOB = Employee.from(BLUTH_COMPANY, "Bluth", "Gob");
	public static final Employee LINDSEY = Employee.from(BLUTH_COMPANY, "Bluth", "Lindsey");
	public static final Employee BUSTER = Employee.from(BLUTH_COMPANY, "Bluth", "Buster");
	public static final Employee GEORGE_MICHAEL = Employee.from(BANANA_STAND, "Bluth", "George Michael");
	public static final Employee MAEBY = Employee.from(BANANA_STAND, "Funke", "Maeby");

	private Employees() {
		// no instantation
	}
}
