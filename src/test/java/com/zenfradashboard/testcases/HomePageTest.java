package com.zenfradashboard.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.Dashboard;
import com.zenfra.dashboard.LoginPage;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	AzenfraHome homePage;
	Dashboard dashboard;

	public HomePageTest() {
		super();
	}

	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
		dashboard = new Dashboard();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new AzenfraHome();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		// TestUtil.SwitchToChildWindow();
		// TestUtil.WaitTill_PageLoads(10);
	}

	@BeforeMethod
	public void moveToHome() {
		dashboard.navigateToDashboard();
	}

	@Test(priority = 1)
	public void verifyHomePageIconsTest() {

		String title = driver.getTitle();
		System.out.println(title);
		softAssert.assertEquals(homePage.verifyPageTitle(), "ZENfra");
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void selectDashboard() {

		System.out.println("Dashboard");
	}

	@AfterTest
	public void tearDown() {

		driver.close();
		driver.quit();
	}

}
