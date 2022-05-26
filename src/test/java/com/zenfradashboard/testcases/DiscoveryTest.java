package com.zenfradashboard.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.Discovery;
import com.zenfra.dashboard.LoginPage;

public class DiscoveryTest extends TestBase {
	LoginPage loginPage;
	AzenfraHome homePage;
	Discovery discovery;

	public DiscoveryTest(){
		super();
	}

	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		discovery = new Discovery();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test (priority = 1)
	public void verifyReportLoading() throws InterruptedException {
		discovery.navigateToAnalyticsReport();
		discovery.verifyReport();
	}
	@Test (priority = 2)
	public void verifyPinnedState() throws InterruptedException {
		discovery.verifyPinnedState();
	}
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
