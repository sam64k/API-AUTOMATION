package com.theverygroup.lf.steps;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Then;

public class ResponseValidation extends ScenarioContext{
	public ScenarioContext scenarioContext;
	public ResponseValidation(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	
	@Then("validate response status code is {string}")
	public void validate_response_status_code_is(String ExpectedStatusCode) {
		int statusCode = scenarioContext.getResponse().getStatusCode();
		assertEquals(ExpectedStatusCode,String.valueOf(statusCode));
	}
	
	@Then("validate response body {string} is {string}")
	public void validate_response_body_is(String jsonPath, String expectedValue) {
		String actualValue = scenarioContext.getResponse().path(jsonPath);
		assertEquals(expectedValue,String.valueOf(actualValue));
		//System.out.println("response :"+this.getResponse().path(key).toString());
		
	    
	}
	@Then("User captures {string} from response as {string}")
	public void user_captures_from_response_as(String path, String key) {
	    // Write code here that turns the phrase above into concrete actions
		String value = scenarioContext.getResponse().path(path);
	    System.out.println("ACCESS TOKEN :"+value);
	    GlobalContext.addToGlobalDataContext(key, value);
	}
}
