package com.employeeapi.testcase;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_DeleteAnEmployee extends TestBase {
	
	String emp;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException{
		
		logger.info("------------- Delete Employee Test Case ---------------");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		JsonPath jpath = response.jsonPath();
		
		emp = (String) jpath.getList("id").get(0);
		
		logger.info("Deleting Employee with id " + emp);
		
		response = httpRequest.request(Method.DELETE, "/delete/" +emp);
		
		Thread.sleep(3000);
		
	}
	
	@Test
	void checkReponseMessage(){
		logger.info("Checking Response after delete employee");
		logger.info("Delete Response is " +response.getBody().asString());
		Assert.assertTrue(response.getBody().asString().contains("successfully! deleted Records"));
	}
	
	@Test(dependsOnMethods="checkReponseMessage")
	void checkEmpExistsAfterDelete(){
		logger.info("Checking employee exists after delete");
		response = httpRequest.request(Method.GET, "/employee/" +emp);
		//Assert.assertFalse(response.getBody().asString().contains(emp));
	}
	
	@AfterClass
	void teardown(){
		logger.info("************** Delete Employee Test case ended ********************");
	}

}
