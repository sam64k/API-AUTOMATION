package com.theverygroup.lf.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.theverygroup.lf.browsersession.ChromeBrowser;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class OcwmsPortalSteps {
	
	public ScenarioContext scenarioContext;
	public OcwmsPortalSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	private WebDriver driver;
	@Given("I navigates to {string}")
	public void i_navigates_to(String url) {
	    ChromeBrowser.launchSession();
	    driver = ChromeBrowser.getSession();
	    driver.get(url);
	}
	@Given("I close browser session")
	public void i_close_browser_session() {
		ChromeBrowser.closeDriverSession();
	}
	@Given("I login into ocwms portal")
	public void i_login_into_ocwms_portal() {
		driver = ChromeBrowser.getSession();
		String userName,password;
		userName = GlobalContext.getCredentials("ocwms.username");
		password = GlobalContext.getCredentials("ocwms.password");
	    driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
	    driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//button[@id='submit']")).click();
	    
	    List<WebElement> elements = driver.findElements(By.xpath("//span[@title='Close']"));
	    Actions action = new Actions(driver);
	    for(WebElement e: elements) {
	    	action.moveToElement(e).click().perform();
	    }
	}
	
	@Given("I navigate to {string} TAB")
	public void i_navigate_to_tab(String menu) {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.xpath("//span[@id='lgf_main_menu']")).click();
	    driver.findElement(By.xpath("//*[@dijitpopupparent='lgf_main_menu']//*[text()='"+menu+"']")).click();
	}
	
	@Given("I search for purchase order number {string}")
	public void i_search_for_purchase_order_number(String poNumber) {
		driver.findElement(By.xpath("//span[@role='button' and @title='Search']")).click();
		driver.findElement(By.xpath("//span[@name='PurchaseOrderHdrView.search_pane.clear']")).click();
	    driver.findElement(By.xpath("//input[@id='dijit_form_TextBox_1']")).sendKeys(poNumber);
	}
	@Given("I search for purchase order number")
	public void i_search_for_purchase_order_number() {
		String poNumber = scenarioContext.getPayloadParams().get("contractNumber") + "0" +
				scenarioContext.getPayloadParams().get("callNumber");
		driver.findElement(By.xpath("//span[@role='button' and @title='Search']")).click();
		driver.findElement(By.xpath("//span[@name='PurchaseOrderHdrView.search_pane.clear']")).click();
	    driver.findElement(By.xpath("//input[@id='dijit_form_TextBox_1']")).sendKeys(poNumber);
	    System.out.println("PO NUMBER:" + poNumber);
	}
	@Then("I click search button")
	public void i_click_search_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(35000);
	
		driver.findElement(By.xpath("//span[contains(@name,'search_pane.search')]")).click();
	}
	@Then("I validate {string} is {string}")
	public void i_validate_is(String columnHeader, String expected) throws InterruptedException {	
	    //find column index
		WebElement element = driver.findElement(By.xpath("//div[contains(@name,'View.grid')]//table//th//div[text()='"+columnHeader+"']/div"));
	    String str = element.getAttribute("class");
	    int startIndex = str.lastIndexOf("-");
	    String columnIndex = "dgrid-column-"+str.substring(startIndex+1, str.length());
	    
	    //scroll into view
	    element = driver.findElement(By.xpath("//div[contains(@name,'View.grid')]//table//td[contains(@class,'"+columnIndex+"')]"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    
	    String actual = driver.findElement(By.xpath("//div[contains(@name,'View.grid')]//table//td[contains(@class,'"+columnIndex+"')]")).getText();
	    
	    System.out.println("element : " + actual);
	    // highlight element before taking screenshot
	    if(expected != actual) {
	    	JavascriptExecutor jse = (JavascriptExecutor) driver;
	    	jse.executeScript("arguments[0].style.border='2px solid red'", element);
	    }
		
	    assertEquals(expected,actual);
	}
	@Then("I validate Po nbr")
	public void i_validate_po_nbr() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		this.i_validate_is("PO Nbr",GlobalContext.getGlobalDataContext("contractNumber"));
	}
	
	@Given("I navigate to ASN screen")
	public void i_navigate_to_asn_screen() {
		//driver.findElement(By.xpath("//*[@title='Close']/span")).click();
	    List<WebElement> elements = driver.findElements(By.xpath("//span[@title='Close']"));
	    Actions action = new Actions(driver);
	    for(WebElement e: elements) {
	    	action.moveToElement(e).click().perform();
	    }
	    driver.findElement(By.xpath("//span[@id='lgf_main_menu']")).click();
	    driver.findElement(By.xpath("//*[@dijitpopupparent='lgf_main_menu']//*[text()='ASN']")).click();
	  //*[@role='region' and @dijitpopupparent='lgf_main_menu']//*[text()='Purchase Orders']

	}
	@Given("I search for purchase order number {string} on ASN screen")
	public void i_search_for_purchase_order_number_on_asn_screen(String poNum) {
		driver.findElement(By.xpath("//span[@role='button' and @title='Search']")).click();
	    driver.findElement(By.xpath("//span[@name='IBShipmentView.search_pane.clear']")).click();
	    WebElement element = driver.findElement(By.xpath("//*[@id='dijit_form_TextBox_2']"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    driver.findElement(By.xpath("//*[@id='dijit_form_TextBox_2']")).sendKeys(poNum);
	}
	
	@Given("I search for purchase order number on ASN screen")
	public void i_search_for_purchase_order_number_on_asn_screen() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[@role='button' and @title='Search']")).click();
	    driver.findElement(By.xpath("//span[@name='IBShipmentView.search_pane.clear']")).click();
	    WebElement element = driver.findElement(By.xpath("//*[@id='dijit_form_TextBox_2']"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    
	    String poNum = GlobalContext.getGlobalDataContext("contractNumber");
	    driver.findElement(By.xpath("//*[@id='dijit_form_TextBox_2']")).sendKeys(poNum);
	}
	@Then("wait for {string} sec")
	public void i_wait_for_sec(String time) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("TIME:"+Integer.parseInt(time));
		int waitTime = Integer.parseInt(time)*1000;
		System.out.println("TIME:"+waitTime);
		Thread.sleep(waitTime);  
	}

}
