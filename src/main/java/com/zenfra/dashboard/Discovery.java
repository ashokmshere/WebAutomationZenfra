package com.zenfra.dashboard;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zenfra.base.TestBase;
import com.zenfra.util.TestUtil;

public class Discovery extends TestBase {
	TestUtil testUtilObject;

	@FindBy(xpath = "//*[@id='logo']")
	WebElement logo;

	@FindBy(xpath = "//a[text()='Migration']")
	WebElement migraton;
	@FindBy(xpath = "//a[text()='Discovery']")
	WebElement discocvery;

	@FindBy(xpath = "//a[text()='VMAX Server']")
	WebElement vMaxSever;
	@FindBy(id = "reportSection")
	WebElement discoveryReport;

	@FindBys(@FindBy(xpath="//div[@aria-colindex='1' and contains(@class, 'ag-cell-last-left-pinned')]"))
	List<WebElement> reportPinnedElements;
	public Discovery() {
		PageFactory.initElements(driver, this);
		testUtilObject = new TestUtil();
	}

	public void navigateToAnalyticsReport() throws InterruptedException {
		migraton.click();
		discocvery.click();
		testUtilObject.waitforLoadingIcon();
		testUtilObject.waitforElementClickable(vMaxSever);
		Thread.sleep(500);
		vMaxSever.click();
		Thread.sleep(500);
		testUtilObject.waitforLoadingIcon();
	}
	public void verifyReport() {
		testUtilObject.waitforElementDisappear(discoveryReport);
		Assert.assertTrue(discoveryReport.isDisplayed(), "Report verification");
	}
	public void verifyPinnedState() {
		testUtilObject.waitforElementDisappear(discoveryReport);
		if (reportPinnedElements.size() > 0 ) {
			Assert.assertTrue(true, "Report first column is pinned");
		}
		else {
			Assert.assertTrue(false, "Report first column is pinned");
		}
	}

}
