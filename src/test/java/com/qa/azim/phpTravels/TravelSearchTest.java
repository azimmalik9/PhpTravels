package com.qa.azim.phpTravels;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qa.azim.DemoSite.LandingPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;


public class TravelSearchTest {

	WebDriver driver = null;
	Actions action = null;
	ExtentReports report = null;
	ExtentTest test = null;
	
	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		
		driver = new ChromeDriver();		
		action = new Actions(driver);
		
		report = new ExtentReports("C:\\Users\\Azim\\Desktop\\report\\" + "PhptravelsTest.html", true);

		test = report.startTest("php Travel booking search");
		
		driver.manage().window().maximize();
		
		driver.get("http://phptravels.net/");
		test.log(LogStatus.INFO, "navigate to phpTravel homepage");
}
	
			
	/**
	 * Shows available hotels for next day
	 */
	@Test
	public void nextDayHotel() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		//homePage.locationLookup(action);
		test.log(LogStatus.INFO, "Select Checkin Date");
		homePage.checkIn();
		test.log(LogStatus.INFO, "Checkin Date Selected");
		test.log(LogStatus.INFO, "Select Checkout Date");
		homePage.checkOut();
		test.log(LogStatus.INFO, "Checkout Date Selected");
		test.log(LogStatus.INFO, "Select number of travellers");
		homePage.selectNumPeople();
		test.log(LogStatus.INFO, "Number of travellers selected");
		
		//SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
		SearchPage searchPage = homePage.submit();
		test.log(LogStatus.INFO, "Search query submitted");
		assertEquals(searchPage.getTitle(), "Search Results");
		test.log(LogStatus.INFO, "Arrive at Hotel Search page");
		
		
		searchPage.clickLink();
		searchPage.clickLink2();
	}	
	
	@After
	public void tearDown() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
