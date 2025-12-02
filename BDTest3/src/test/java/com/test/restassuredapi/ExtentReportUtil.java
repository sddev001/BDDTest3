package com.test.restassuredapi;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	

	@BeforeTest
	public void setup(ITestContext context) {	
		extentTest = extentReports.createTest(context.getName());
	}


	
	@BeforeSuite
	public void initialiseExtentReports() {
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("AllTests.html");
		sparkReporter_all.config().setReportName("All Tests report");
		
		ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("FailedTests.html");
		sparkReporter_failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		sparkReporter_failed.config().setReportName("Failure report");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all, sparkReporter_failed);
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}
	
	@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
	}
	
	@AfterMethod
	public void checkStatus(Method m, ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(m.getName() + " is FAIL");
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName() + " is PASS");
		}
	}

}
