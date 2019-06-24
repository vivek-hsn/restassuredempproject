package com.employeeapi.testcase;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.EmpUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC003_CreateAnEmployee extends TestBase {

	String emp_name = EmpUtils.getEmpName();
	String emp_salary = EmpUtils.getSalary();
	String emp_age = EmpUtils.getEmpAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {

		logger.info("----------------- Create Employee Test Case Started -----------------");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		JSONObject empdetails = new JSONObject();

		empdetails.put("name", emp_name);
		empdetails.put("salary", emp_salary);
		empdetails.put("age", emp_age);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(empdetails.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(3000);
	}

	@Test
	void checkResponse() {

		logger.info("Checking create employee response");
		JsonPath jpath = response.jsonPath();
		logger.info("Response body is " + response.getBody().asString());
		Assert.assertEquals(jpath.get("name"), emp_name);
		Assert.assertEquals(jpath.get("salary"), emp_salary);
		Assert.assertEquals(jpath.get("age"), emp_age);

	}

	@Test
	void checkStatusCode() {

		logger.info("Check status code");
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	void chcekStatusLine() {
		logger.info("Check Status Line");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("Check Content type");
		Assert.assertEquals(response.header("Content-Type"), "text/html; charset=UTF-8");
	}

	@Test
	void checkContentEncoding() {
		logger.info("Check Content Encoding");
		Assert.assertEquals(response.header("Content-Encoding"), "gzip");
	}

	@Test
	void checkServerType() {
		logger.info("Check Server Type");
		Assert.assertEquals(response.header("Server"), "nginx/1.14.1");
	}

	@AfterClass
	void teardown() {
		logger.info("************** Create employee test case ended *******************");
	}

}
