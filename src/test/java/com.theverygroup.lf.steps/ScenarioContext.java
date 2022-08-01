package com.theverygroup.lf.steps;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;

public class ScenarioContext {
	private String URI;
	private Map<String,String> headerParams = new HashMap<String,String>();
	private Map<String, String> payloadParams  = new HashMap<String,String>();
	private String payload=null;
	private Response response=null;
	private String userName = null;
	private String password = null;
	private static Scenario scenario;
	
	public static Scenario getScenario() {
		return scenario;
	}
	public static void setScenario(Scenario scenario) {
		ScenarioContext.scenario = scenario;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String URI) {
		this.URI = URI; 
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getUsername() {
		return this.userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void addHeaderParams(String Key,String Value) {
		this.headerParams.put(Key, Value);
	}
	public Map<String,String> getHeaderParams(){
		return headerParams;
	}
	public void addPayloadParams(String Key,String Value) {
		this.payloadParams.put(Key, Value);
	}
//	public void addPayloadHashMap(Map<String, String> payloadParamstemp) {
//		this.payloadParams = payloadParamstemp;
//	}
	public Map<String,String> getPayloadParams(){
		return payloadParams;
	}
	public String getPayloadParams(String key){
		return payloadParams.get(key);
	}
	public String getPayload() {
		return this.payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Response getResponse() {
		return this.response;
	}
}
