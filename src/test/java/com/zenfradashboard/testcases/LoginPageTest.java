package com.zenfradashboard.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zenfra.base.TestBase;
import com.zenfra.dashboard.AzenfraHome;
import com.zenfra.dashboard.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	AzenfraHome homePage;


	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test
	public void loginTest() throws InterruptedException {
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test
	public void resetPassword() {
		//loginPage.lockAccount(prop.getProperty("username"));
		loginPage.naviagteToResetpassword();
		loginPage.verifyInvalidEmail();
		//loginPage.verifyValidEmail(prop.getProperty("username"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
