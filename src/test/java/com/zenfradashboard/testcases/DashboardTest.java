package com.zenfradashboard.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.Dashboard;
import com.zenfra.dashboard.LoginPage;

public class DashboardTest extends TestBase{
	
	LoginPage loginPage;
	AzenfraHome homePage;
	Dashboard dashboard;
	
	public DashboardTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		dashboard = new Dashboard();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyaddChart() throws InterruptedException {
		
		dashboard.addChart();
					
	}
	
	@Test(priority=2)
	public void verifyRemoveChart() throws InterruptedException {
		
		dashboard.removeChart();
					
	}
	
	@Test(priority=3)
	public void verifyAddChartCancel() throws InterruptedException {
		
		dashboard.addChartCancelButton();
					
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
