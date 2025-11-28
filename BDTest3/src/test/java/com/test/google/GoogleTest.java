package com.test.google;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class GoogleTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.google.com");
	}
	
	@Test
	public void verifyTitle() throws InterruptedException {
		//google title
		Thread.sleep(1000);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Google";
		Assert.assertEquals(actualTitle, expectedTitle);
		//google logo
		Thread.sleep(1000);
		Boolean flag = driver.findElement(By.className("lnXdpd")).isDisplayed();
		Assert.assertTrue(flag);
		Thread.sleep(1000);
	}
/*	
	@Test
	public void verifyLogo() {
		Boolean flag = driver.findElement(By.className("lnXdpd")).isDisplayed();
		Assert.assertTrue(flag);
	}
*/	
	@AfterMethod
	public void teardown() {
//		driver.close();
		driver.quit();
	}
}
