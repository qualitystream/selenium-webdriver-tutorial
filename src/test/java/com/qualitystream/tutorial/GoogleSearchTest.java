package com.qualitystream.tutorial;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.JavascriptExecutor;


public class GoogleSearchTest {
	
	// The string is in the constant. Avoid repeat strings on code use constants
	// instead.
	private static final String SEARCH_PHRASE = "quality-stream Introducción a la Automatización de Pruebas de Software";
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/gecko/linux/64/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}
	
	@Test
	public void testGooglePage() throws InterruptedException {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys(SEARCH_PHRASE);
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(10000);
		System.out.println(driver.getTitle());
		// This is a little different from the original code.
		assertTrue(driver.getTitle().indexOf(SEARCH_PHRASE) != -1);
		// The original was:
		// assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Google Search", driver.getTitle());
		// However, due internalization that's assertEquals maybe fail.
		// driver.getTitle() must end with the string "- Google Search", but if the
		// browser is in Spanish the end of the string will be: "- Buscar con Google"
		// causing a false negative, 'cause the search string is part of the
		// driver.getTitle()
		// In this case the better solution is to check if the search string is part of
		// the driver.getTitle()
		// If driver.getTitle().indexOf(SEARCH_PHRASE) returns a value different to -1,
		// the search string is contained inside, no matter where.
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
}
