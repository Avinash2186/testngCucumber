package com.TestRunner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
//import cucumber.runtime.model.CucumberFeature;

@CucumberOptions(
        features = "./features",
        glue = {"stepDefinations"}, 
        monochrome = true,
        //strict = false,
       // dryRun = true,
       /* plugin = { "pretty", "html:target/htmlreports" }*/
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
        		"pretty", "html:target/htmlreports",
        		"json:target/cucumber.json",
        		"junit:target/cucumber.xml"}
        //tags = {"@searchItems"}

        		)

public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		System.out.println("inside setup......");
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		System.out.println("exit setup......");
	}
	
	@Test(dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		System.out.println("inside feature.......");
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
		System.out.println("exit featres");
	}
	
	@DataProvider
	public Object[][] features(){
		return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		Reporter.loadXMLConfig(new File("config/report.xml"));
		testNGCucumberRunner.finish();
	}

}





