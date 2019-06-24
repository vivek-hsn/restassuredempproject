package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Title of the report
		htmlReporter.config().setReportName("Rest Assured Test"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Vivek");

	}

	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName()); // Create new entry in the report

		test.log(Status.PASS, "Test case Passed is " + result.getName()); //Print in log file
	}
	
	public void onTestFailure(ITestResult result){
		test = extent.createTest(result.getName());
		
		test.log(Status.FAIL, "Test case Failed is " + result.getName()); // Add name in extented report
		test.log(Status.FAIL, "Test Case FAILED is " + result.getThrowable()); //to add error/exception in extent report
	}
	
	public void onTestSkipped(ITestResult result){
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is " + result.getName());
	}
	
	public void onFinish(ITestContext testContext){
		extent.flush();
	}
}
