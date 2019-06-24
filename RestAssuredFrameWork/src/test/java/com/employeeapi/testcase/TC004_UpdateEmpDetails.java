package com.employeeapi.testcase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.EmpUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC004_UpdateEmpDetails extends TestBase {

	String name = EmpUtils.getEmpName();
	String salary = EmpUtils.getSalary();
	String age = EmpUtils.getEmpAge();

	@BeforeClass
	void updateEmpDetails() throws InterruptedException {

		logger.info("------------------ Update Employee Test Case Started --------------------");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		JSONObject jobj = new JSONObject();

		jobj.put("name", name);
		jobj.put("salary", salary);
		jobj.put("age", age);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jobj.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/" + empId);

		Thread.sleep(3000);

	}

	@Test
	void checUpdateDetails() {

		logger.info("Checking updated employee records");
		
		logger.info("Response is => " + response.getBody().asString());
		JsonPath jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("name"), name);
		Assert.assertEquals(jpath.get("salary"), salary);
		Assert.assertEquals(jpath.get("age"), age);

	}
	
	@Test
	void checkStatusCode(){
		logger.info("Checking Status Code");
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	void checkStatusLine(){
		logger.info("Checking Status Line");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}

	@AfterClass
	void teardown() {
		logger.info("***************** Update Employee test case ended ****************");
	}

}
