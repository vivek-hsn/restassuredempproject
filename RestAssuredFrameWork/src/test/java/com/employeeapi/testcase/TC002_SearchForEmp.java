package com.employeeapi.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_SearchForEmp extends TestBase {

	@BeforeClass
	public void searchForEmp() throws InterruptedException {

		logger.info("---------------- Search Employee Test Case Started ----------------------");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employee/" + empId);

		Thread.sleep(3000);
	}

	@Test
	public void checkEmployeeDetails() {

		logger.info("Checking employee details");
		String body = response.getBody().asString();
		logger.info("Response body is => " + body);
		Assert.assertTrue(body.contains(empId));
	}

	@Test
	void checkStatusCode() {
		
		logger.info("Checking Status Code");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine(){
		
		logger.info("Checking Status Line");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkCookies(){
		
		logger.info("Checking Cookies");
		logger.info(response.getCookie("PHPSESSID"));
		
	}
	
	@Test
	void checkServer(){
		
		logger.info("Checking Server Type");
		String server = response.getHeader("Server");
		logger.info("Server type is " +server);
		Assert.assertEquals(server, "nginx/1.14.1");
	}
	
	@AfterClass
	void teardown(){
		logger.info("************ Search Employee Test case ended ******************");
	}
	
}
