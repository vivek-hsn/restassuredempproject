package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class EmpUtils {

	public static String getEmpName() {

		String generatedString = RandomStringUtils.randomAlphanumeric(3);
		return "RestAssured" + "-" + generatedString;
	}

	public static String getSalary() {

		String generatedString = RandomStringUtils.randomNumeric(6);

		return generatedString;
	}

	public static String getEmpAge() {

		String generatedString = RandomStringUtils.randomNumeric(2);
		return generatedString;
	}

}
