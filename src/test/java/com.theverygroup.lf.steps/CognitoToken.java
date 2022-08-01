package com.theverygroup.lf.steps;//package com.theverygroup.lf.steps;
//
//import static io.restassured.RestAssured.given;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.response.Response;
//
//public class CognitoToken extends ScenarioContext {
//	static String access_token;
//	String client_id;
//	String client_secret;
//	Response response;
//	
//	public ScenarioContext scenarioContext;
//	public CognitoToken(ScenarioContext scenarioContext) {
//		this.scenarioContext = scenarioContext;
//	}
//		
//	@Given("Cognito token endpoint is {string}")
//	public void cognito_token_endpoint_is(String uri) {
//	    scenarioContext.setURI(uri);
//	}
//
//	@Given("User connects cognito with {string}")
//	public void user_connects_cognito_with(String credential) {
//	    
//		String str = System.getenv(credential);
//	    String cred[]=str.split(":");
//		client_id = cred[0];
//    	client_secret = cred[1];
//    	if(client_id.equals(null) && client_secret.equals(null))
//    		throw new NullPointerException("Client credentials are null..!!");	
//	}
////	@When("User generates a valid OAuth2 cognito token")
////	public void user_generates_a_valid_o_auth2_cognito_token() {
////		
////		response = given()
////				.auth()
////					.preemptive()
////					.basic(client_id, client_secret)
////					.param("grant_type","client_credentials")
////				.post(scenarioContext.getURI()).then().extract().response();
////		
////		scenarioContext.setResponse(response);
////	}
//
//	@Then("User captures {string} from response body")
//	public void user_captures_from_response_body(String key) {
//	    
//		access_token = response.path(key);
//	    System.out.println("ACCESS TOKEN :"+access_token);
//	}
//
//}
