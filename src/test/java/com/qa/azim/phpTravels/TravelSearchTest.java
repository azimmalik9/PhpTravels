package com.qa.azim.phpTravels;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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

		test = report.startTest("Demo Site Registration");
		
		driver.manage().window().maximize();
		
		driver.get("http://phptravels.net/");
}
	
			
	/**
	 * Shows available hotels for next day
	 */
	@Test
	public void nextDayHotel() {
		driver.get("http://www.phptravels.net");
		HomePage homePage = new HomePage(driver);
		//homePage.locationLookup(action);
		homePage.checkIn();
		homePage.checkOut();
		homePage.selectNumPeople(3, 0);
		
		SearchPage searchPage = homePage.submit();
		assertEquals(searchPage.getTitle(), "Search Results");
		
		
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
