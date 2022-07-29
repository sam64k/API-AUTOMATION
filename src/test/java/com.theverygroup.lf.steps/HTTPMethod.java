package com.theverygroup.lf.steps;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;

public class HTTPMethod {
	
	public static Response httpPut(Map<String,String> headers,String payload,String URI) {
	
		return  given()
				.headers(headers)
				.body(payload)
			.put(URI)
				.then()
			.extract().response(); 
	}
	public static Response httpPost(Map<String,String> headers,String payload,String URI,String username,String password) {
		return  given()
					.auth().preemptive().basic(username, password)
				.when()
					.headers(headers)
					.body(payload)
					.post(URI)
				.then()
					.extract().response();
	}
	public static Response httpGetToken(String uri,String client_id,String client_secret ) {
		return given()
					.auth()
					.preemptive()
					.basic(client_id, client_secret)
					.param("grant_type","client_credentials")
				.post(uri)
					.then().extract().response();
	}
	
}
