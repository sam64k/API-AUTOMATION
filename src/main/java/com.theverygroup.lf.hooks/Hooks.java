package com.theverygroup.lf.hooks;

import com.theverygroup.lf.browsersession.ChromeBrowser;
import com.theverygroup.lf.steps.GlobalContext;
import com.theverygroup.lf.steps.ScenarioContext;
import com.theverygroup.lf.utility.EncryptDecrypt;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.http.Headers;
import io.restassured.response.ResponseBody;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Hooks {
	public ScenarioContext scenarioContext;
	public Hooks(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	
	@Before
	public void initScenario(Scenario sc) {
		ScenarioContext.setScenario(sc);
	}
	@BeforeAll(order=1)
	public static void readPropertiesFile() throws Throwable {
		System.out.println("Before All Hook ## Read Property File");
//		GlobalContext.setProperties("src\\main\\resources\\configuration.xml");
		//String key ="udelsaWKSswwOOQ!@Awdnj#";
		String key = System.getenv("SECRET_KEY");
		System.out.println("key:::::"+key);

		InputStream fis2 = Hooks.class.getClassLoader().getResourceAsStream("properties.encrypt");

		FileOutputStream fos2 = new FileOutputStream("properties.decrypt");
		EncryptDecrypt.decrypt(key, fis2, fos2);
		EncryptDecrypt.readproperties("properties.decrypt");
	}
	@BeforeAll(order=2)
	public static void readEnvironmentEndpoint() throws IOException {
		//readEnvironmentEndpoint()
		InputStream input = Hooks.class.getClassLoader().getResourceAsStream("env.properties");
		Properties prop = new Properties();
		prop.load(input);
		GlobalContext.setEnvironmentURI(prop);
	}
	@After
	public static void closeChromeSessionWhenTestFail(Scenario scenario) {
		System.out.println("Scenario Hook:Cose browser session when test failures");
		WebDriver driver = ChromeBrowser.getSession();
		try {
			if(scenario.isFailed() && driver != null) {
				//capture screenshot when test failed
				byte [] screenshot = ((org.openqa.selenium.TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
			}
		}
		finally {
			//close browser if scenario fails
			ChromeBrowser.closeDriverSession();
		}
	}

	//@AfterStep


	
	@After
	public void logRequestInCucumberReports(Scenario sc) {
		StringBuilder str = new StringBuilder();
		
		if(scenarioContext.getResponse()!= null) {
			str.append("@URI : ").append(scenarioContext.getURI()).append("\r\n");
			str.append("@REQUEST-HEADER : ")
			.append(scenarioContext.getHeaderParams().toString()).append("\r\n");
			if(scenarioContext.getPayload() == null) {
				str.append("@REQUEST-BODY : ").append("{empty body}").append("\r\n");
			}
			else {
				str.append("@REQUEST-BODY : ").append(scenarioContext.getPayload()).append("\r\n");
			}
			Headers responseHeader = scenarioContext.getResponse().getHeaders();
			ResponseBody responseBody = scenarioContext.getResponse().getBody();
			str.append("\r\n");
			str.append("@RESPONSE-STATUS : ").append(scenarioContext.getResponse().getStatusLine()).append("\r\n");
			//str.append("RESPONSE-HEADER@").append("\r\n");
			str.append(responseHeader.toString()).append("\r\n");
			str.append("@RESPONSE-BODY : ").append("\r\n");
			str.append(responseBody.asPrettyString()).append("\r\n");
			sc.attach(str.toString(), "text/plain", "Request Spec");
		}
	}
}
