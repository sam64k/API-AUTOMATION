package com.theverygroup.lf.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.text.StringSubstitutor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

public class HTTPRequestBuilder extends ScenarioContext {

	String payloadTemp=null;
	
	public ScenarioContext scenarioContext;
	public HTTPRequestBuilder(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	
	@Given("User include header params {string} as {string}")
	public void user_include_header_params_as(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		scenarioContext.addHeaderParams(key, value);
	}
//	@Given("User includes OAuth token in request header")
//	public void include_o_auth2_token_in_header() {
//		if(CognitoToken.access_token != null) {
//			scenarioContext.addHeaderParams("Authorization","Bearer "+CognitoToken.access_token);
//		}
//		else 
//			throw new NullPointerException("OAUTH token is null!!");
//		
//	}
	@Given("User includes {string} in request header")
	public void user_includes_in_request_header(String token) {
	    // Write code here that turns the phrase above into concrete actions
		if(GlobalContext.getGlobalDataContext(token) != null) {
			scenarioContext.addHeaderParams("Authorization","Bearer "+GlobalContext.getGlobalDataContext(token));
		}
		else 
			throw new NullPointerException("OAUTH token is null!!");
	}
	@Then("User set {string} in request body with value {string}")
	public void user_set_in_request_body_with_value(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	    scenarioContext.addPayloadParams(key, value);
	}
	@Then("I generate a valid contractNumber sarting with {string}")
	public void i_generate_a_valid_contract_number_sarting_with(String startsWith) {
	    // Write code here that turns the phrase above into concrete actions
		Random rad = new Random();
		String contractNumber;
		
		contractNumber = startsWith + String.format("%04d", rad.nextInt(9999));
		scenarioContext.addPayloadParams("contractNumber", contractNumber);
		System.out.println("contractNumber:"+contractNumber);
		
		//GlobalContext.setcontractNumber(contractNumber);
	}
	@Then("I set purchaseOrderId")
	public void i_set_purchase_order_id() {
	    // Write code here that turns the phrase above into concrete actions
		
	}
	
	@Then("I set purchaseOrderId same as {string}")
	public void i_set_purchase_order_id_same_as(String key) {
	    // Write code here that turns the phrase above into concrete actions
		if(GlobalContext.getGlobalDataContext(key) != null) {
			scenarioContext.addPayloadParams("purchaseOrderId", GlobalContext.getGlobalDataContext(key));
			System.out.println("set: "+GlobalContext.getGlobalDataContext(key));
		}
		else
			throw new NullPointerException("purchaseOrderId is NULL!!");
	}	
	@Given("I set the request body params")
	public void i_set_the_request_body_params(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		//scenarioContext.addPayloadHashMap(dataTable.asMap(String.class,String.class));
		Map<String,String> params = dataTable.asMap(String.class,String.class);
		for(String key:params.keySet()) {
			if(!key.toLowerCase().equals("key"))
				scenarioContext.addPayloadParams(key, params.get(key));
		}
	    System.out.println("body Params:"+scenarioContext.getPayloadParams().toString());
	}
	
	@Then("User build request payload")
	public void user_build_request_payload() {
	    // Write code here that turns the phrase above into concrete actions
		StringSubstitutor sub = new StringSubstitutor(scenarioContext.getPayloadParams());
		String payload = scenarioContext.getPayload();
		scenarioContext.setPayload((sub.replace(payload)));
		System.out.println("Payload:"+scenarioContext.getPayload());
			
	}
	@Then("The API endpoint is {string}")
	public void the_api_endpoint_is(String URI) {
	    // Write code here that turns the phrase above into concrete actions
		scenarioContext.setURI(URI);
	}
	@When("user performs HTTP {string} request")
	public void user_performs_http_request(String httpOperation) {
	    // Write code here that turns the phrase above into concrete actions
		Response response =  HTTPMethod.httpPut(scenarioContext.getHeaderParams(), scenarioContext.getPayload(), scenarioContext.getURI());
		scenarioContext.setResponse(response);
	}
	@Then("user performs HTTP {string} request on InventorySummey API")
	public void user_performs_http_request_on_inventory_summey_api(String string) {
	    // Write code here that turns the phrase above into concrete actions
//		String us="ivstvgtest";
//		String pw="3Kk6nphN";
	    Response resp = HTTPMethod.httpPost(scenarioContext.getHeaderParams(), 
	    		scenarioContext.getPayload(), scenarioContext.getURI(),
	    		scenarioContext.getUsername(),scenarioContext.getPassword());
	    scenarioContext.setResponse(resp);
	}
	
	@Then("read payload from file {string}")
	public void read_payload_from_file(String filename) throws IOException, URISyntaxException {
	    // Write code here that turns the phrase above into concrete actions
		URL systemResource = ClassLoader.getSystemResource(String.format("%s/%s", "payload/", filename));
		scenarioContext.setPayload(new String(Files.readAllBytes(Paths.get(systemResource.toURI()))));
	}
	
	@Then("I preserve {string} from request")
	public void i_preserve_from_request(String key) {
	    // Write code here that turns the phrase above into concrete actions
		if(key.equals("contractNumber"))
			GlobalContext.addToGlobalDataContext(key,scenarioContext.getPayloadParams(key)+"02");
		else
			GlobalContext.addToGlobalDataContext(key,scenarioContext.getPayloadParams(key));
	}
	@Given("get secret {string} and {string}")
	public void get_secret_and(String user, String pws) {
	    // Write code here that turns the phrase above into concrete actions
		scenarioContext.setUsername(GlobalContext.getCredentials(user));
		scenarioContext.setPassword(GlobalContext.getCredentials(pws));
	}
	@When("User generates a valid OAuth2 cognito token")
	public void user_generates_a_valid_o_auth2_cognito_token() {
		Response resp = HTTPMethod.httpGetToken(scenarioContext.getURI(), scenarioContext.getUsername(),scenarioContext.getPassword());
		scenarioContext.setResponse(resp);
	}
}
