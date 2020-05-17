package stepDefinations;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelHandler;
import utilities.PropertiesFileReader;
import utilities.TestDataHandler;



public class AmazonStepDefination {
	
	public static WebDriver driver;
	public static String searchItem;
	PropertiesFileReader obj= new PropertiesFileReader();  
	TestDataHandler testDataHandler = new TestDataHandler();
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
	    
		System.out.println("on home page......");
		Properties properties=obj.getProperty();   
		
		WebDriverManager.chromedriver().setup();
		 
        //Create driver object for Chrome
        driver = new ChromeDriver();
		driver.manage().window().maximize();			
		driver.get(properties.getProperty("browser.baseURL"));
		Thread.sleep(3000);
	}

	@When("^User searches for \"([^\"]*)\"$")
	public void user_searches_for(String searchItem) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new PendingException();
		this.searchItem=searchItem;
		System.out.println("searched for "+searchItem);
		Thread.sleep(5000);
		//driver.switchTo().frame(index)
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchItem);
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		Thread.sleep(1000);
	}

	@Then("^products related to search must appear$")
	public void products_related_to_search_must_appear() throws Throwable {
	    System.out.println("Assertion here......");
	    //driver.findElement(By.cssSelector("search-icon-legacy")).click();;	
	    String text = driver.findElement(By.xpath("(//div[contains(@class,'s-result-list')]//h2)[1]")).getText();
	    System.out.println("text:::::::"+text);
	    Assert.assertTrue(text.contains(searchItem), "Product Mismatch in Assertion for Item :::"+searchItem);
		Thread.sleep(3000);	 	
		driver.quit();
	}
	
	@When("^User searches for Data mentioned in an Excel sheet$")
	public void user_searches_for_Data_mentioned_in_an_Excel_sheet() throws Throwable {
	   Map<String, String> Data =  ExcelHandler.getTestDataInMap("./resources-testdata/TestData.xlsx", "Sheet1", "Test4");
	
	   testDataHandler.setTestDataInMap(Data);
	   System.out.println("Data From Sheet ::::"+Data.get("SearchTerm"));
		searchItem = Data.get("SearchTerm");
		  System.out.println("searched for item from Excel "+searchItem);
		  Thread.sleep(5000); //driver.switchTo().frame(index)
		  driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchItem);
		  driver.findElement(By.xpath("//input[@value='Go']")).click();
		  Thread.sleep(1000);	 
	}
	
	@Then("^Read Data from Excel for other Search Terms$")
	public void read_Data_from_Excel_for_other_Search_Terms() throws Throwable {
	   System.out.println("Test Case ID ran DAta From Excel::::"+testDataHandler.getTestDataInMap().get("TestCaseId"));
	    
	}
	
	@When("^User Read DAta From Excel Based on Run as Yes and Test Id$")
	public void user_Read_DAta_From_Excel_Based_on_Run_as_Yes_and_Test_Id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 Map<String, String> Data =  ExcelHandler.getTestDataInMap("./resources-testdata/TestData.xlsx", "Sheet1", "Test4");
		 testDataHandler.setTestDataInMap(Data);
	}

}
