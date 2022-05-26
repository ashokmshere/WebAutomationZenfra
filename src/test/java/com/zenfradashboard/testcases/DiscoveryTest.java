package com.zenfradashboard.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.Dashboard;
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
	@Test
	public void verifyReportLoading() throws InterruptedException {
		discovery.navigateToAnalyticsReport();
		discovery.verifyReport();
	}

}
