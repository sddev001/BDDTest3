package com.test.restassuredapi;

import static io.restassured.RestAssured.baseURI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.restassuredapi.ExtentReportUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPUTApi extends ExtentReportUtil{
	@Test
	public void updateProduct () {
		Map <String, String> jsonRequestBodyAsMap = new HashMap();
		jsonRequestBodyAsMap.put("title", "Gaps");
		jsonRequestBodyAsMap.put("price", "21.99");
		jsonRequestBodyAsMap.put("description", "50%Cotton");
		jsonRequestBodyAsMap.put("category", "women's clothing");
		
		baseURI = "https://fakestoreapi.com/products/1";
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.contentType(ContentType.JSON);
		requestSpec.body(jsonRequestBodyAsMap);
		
		Response response = requestSpec.put();
		System.out.println("PUT \n" + response.getBody().asPrettyString()); 
		
		int statusCode = response.getStatusCode();
		System.out.println("Status Code " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		extentTest.info("PUT request " + baseURI);
		extentTest.info("Request payload " + response.getBody().asPrettyString());
	}

}
