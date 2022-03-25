package com.ubc.qa.automation;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.response.Response;

public class RestAssuredRequest {
	
	// Get method
	public Response getRequestCall(String url, Map<String, String> headers) {
		Response resp = given().log().all().headers(headers).contentType("application/json").when().get(url).then()
				.extract().response();
		return resp;
	}
	

	// Post method - with headers
	public Response sendJsonRequestWithBody(String url, Object object, Map<String, String> headers) {
		Response resp = given()
				.config(RestAssured.config()
						.encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.log().all().headers(headers).and().contentType("application/json").and().accept("application/json")
				.and().body(object).when().post(url).then().extract().response();
		return resp;
	}
	
}
