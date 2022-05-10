package com.zenfradashboard.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.LoginPage;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	AzenfraHome homePage;
		
	public HomePageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new AzenfraHome();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		//TestUtil.SwitchToChildWindow();
		//TestUtil.WaitTill_PageLoads(10);
	}
	
	@Test(priority=1)
	public void verifyHomePageIconsTest() {
		
		String title=driver.getTitle();
		System.out.println(title);
		
		softAssert.assertEquals(homePage.verifyPageTitle(), "ZENfra");

		softAssert.assertAll();
	}
	
	@Test(priority=2)
	public void selectDashboard() {
		
		System.out.println("Dashboard");
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver.quit();
	}
	
	

}
