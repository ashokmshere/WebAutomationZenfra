package com.zenfradashboard.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.Dashboard;
import com.zenfra.dashboard.LoginPage;

public class DashboardTest extends TestBase {

	LoginPage loginPage;
	AzenfraHome homePage;
	Dashboard dashboard;

	public DashboardTest() {
		super();
	}

	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		dashboard = new Dashboard();
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void moveToHome() {
		dashboard.navigateToDashboard();
	}

	@Test(priority = 1)
	public void verifyaddChart() throws InterruptedException {

		dashboard.addChart();

	}

	@Test(priority = 2)
	public void verifyAddChartCancel() throws InterruptedException {

		dashboard.addChartCancelButton();

	}

	@Test(priority = 3)
	public void exportToPDF() throws InterruptedException {
		dashboard.exportTOPDF();
	}

	@Test(priority = 4)
	public void exportTOPPTX() throws InterruptedException {
		dashboard.exportTOPPTX();
	}

	@Test(priority = 5)
	public void verifyRemoveChart() throws InterruptedException {

		dashboard.removeChart();

	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
