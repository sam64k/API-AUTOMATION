package com.theverygroup.lf.steps;

import io.cucumber.java.en.Then;

public class DataChaining {
	
	public ScenarioContext scenarioContext;
	public DataChaining(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	@Then("I preserve {string} for ASN request")
	public void i_preserve_for_asn_request(String key) {
	    // Write code here that turns the phrase above into concrete actions
		String contractNumber = scenarioContext.getPayloadParams().get(key)+
				"0"+scenarioContext.getPayloadParams().get("callNumber");
		GlobalContext.addToGlobalDataContext(key, contractNumber);
	}
	@Then("fetch {string} from previous request and set as {string}")
	public void fetch_from_previous_request_and_set_as(String key, String attribute) {
		String value = GlobalContext.getGlobalDataContext(key);
		scenarioContext.addPayloadParams(attribute, value);
	}

}
