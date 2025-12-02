package com.test.restassuredapi;

import static io.restassured.RestAssured.baseURI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.restassuredapi.ExtentReportUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGETApi extends ExtentReportUtil{
	public class HTTPRequests {
		
		@Test
		public void getUsers() {
			baseURI = "https://api.restful-api.dev/objects";
			RequestSpecification requestSpec = RestAssured.given();
			requestSpec.contentType(ContentType.JSON);
			requestSpec.body(baseURI);
			
			Response response = requestSpec.get();
			System.out.println("GET \n" +  response.getBody().asPrettyString());
			
			int statusCode = response.getStatusCode();
			System.out.println("Status Code " + statusCode);
			Assert.assertEquals(statusCode, 200);
			
			extentTest.info("GET request " + baseURI);
			extentTest.info("Request payload " + response.getBody().asPrettyString());
			

			
		}
	}

}
