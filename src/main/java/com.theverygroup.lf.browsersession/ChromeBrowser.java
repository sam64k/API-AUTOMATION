package com.theverygroup.lf.browsersession;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.theverygroup.lf.steps.ScenarioContext;

import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;


public class ChromeBrowser {
	private static WebDriver session=null;

	public static void launchSession() {
		// TODO Auto-generated method stub
		if(session == null) {
			ChromeDriverManager.chromedriver().setup();
			session = new ChromeDriver();
			session.manage().deleteAllCookies();
			session.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}
	public static WebDriver getSession() {
		return session;
	}
	@Then("capture screen")
	public void capture_screenshot() {
	    // Write code here that turns the phrase above into concrete actions
		if(session != null) {
			this.waitForPageLoad();
			byte [] screenshot = ((org.openqa.selenium.TakesScreenshot)session).getScreenshotAs(OutputType.BYTES);
			ScenarioContext.getScenario().attach(screenshot, "image/png", "screen");
		}
	}
	public void waitForPageLoad() {
	    // wait for page to load
		if(session!=null) {
			WebDriverWait wait = new WebDriverWait(session, Duration.ofSeconds(20));
			wait.until((ExpectedCondition<Boolean>) wd ->
			   ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		}
	}
	
	public static void closeDriverSession() {
		if(session != null) {
			session.manage().deleteAllCookies();
			session.quit();
			session=null;
		}
	}
}
