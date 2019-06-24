package com.employeeapi.testcase;

import org.testng.Assert;
import org.testng.annotations.*;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmployees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		System.out.println("Test case before method");

		logger.info("------------------- Get All employees test cases -----------------------");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(3000);

	}

	@Test
	public void checkResponseBody() {

		logger.info("Checking response body");
		String body = response.getBody().asString();
		logger.info("Response is => " + body);
		Assert.assertTrue(body.contains(empId));
	}

	@Test
	public void checkStatusCode() {

		logger.info("Checking Status code");
		logger.info("Status code is " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test
	public void checkStatusLine() {

		logger.info("Checking Status Line");

		String statusLine = response.getStatusLine();
		logger.info("Status line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	public void checkContentType() {

		logger.info("Checking Content Type");
		logger.info("Content-Type is " + response.getHeader("Content-Type"));
		Assert.assertEquals(response.getHeader("Content-Type"), "text/html; charset=UTF-8");
	}

	@Test
	public void checkContentLength() {

		logger.info("Checking Content length");
		int contentLength = Integer.parseInt(response.getHeader("Content-Length"));
		logger.info("Content length is " + contentLength);

		if (contentLength > 15000) {
			logger.warn("Content length is very big");
		}
		Assert.assertTrue(contentLength > 5000);

	}

	@Test
	public void checkContentEncoding() {

		logger.info("Checking Content Encoding");
		logger.info("Content Encoding is " + response.getHeader("Content-Encoding"));
		Assert.assertEquals(response.getHeader("Content-Encoding"), "gzip");
	}
	
	@Test
	public void checkResponseTime(){
		
		logger.info("Checking response time");
		long time = response.getTime();
		logger.info("Response time is " + time);
		Assert.assertTrue(time<5000);
	}
	
	@AfterClass
	public void teardown(){
		logger.info("****************** Get all employees test case ended *******************");
	}

}
