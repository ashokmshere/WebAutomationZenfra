package com.zenfra.dashboard;

import java.util.ArrayList;
import java.util.Collections;
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

	@FindBy(xpath = "//div[@class='report_grid_data ']")
	WebElement discoveryReport;

	@FindBys(@FindBy(xpath = "//div[@aria-colindex='1' and contains(@class, 'ag-cell-last-left-pinned')]"))
	List<WebElement> reportPinnedElements;

	@FindBys(@FindBy(xpath = "//div[@role='row']/div[@col-id='Initiator Name']"))
	List<WebElement> colIndicator;

	@FindBy(xpath = "//span[@class='ag-header-cell-text' and text()='Initiator Name' and @ref='eText']")
	WebElement colmunIndicator;

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
		testUtilObject.waitforElementClickable(discoveryReport);
		Assert.assertTrue(discoveryReport.isDisplayed(), "Report verification");
	}

	public void verifyPinnedState() {
		testUtilObject.waitforElementClickable(discoveryReport);
		if (reportPinnedElements.size() > 0) {
			Assert.assertTrue(true, "Report first column is pinned");
		} else {
			Assert.assertTrue(false, "Report first column is pinned");
		}
	}

	public void verifyAscending() throws InterruptedException {
		colmunIndicator.click();
		Thread.sleep(500);
		ArrayList<String> obtainedList = new ArrayList<>();
		System.out.println(colIndicator.size());
		if (colIndicator.size() > 0) {

			for (WebElement we : colIndicator) {
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase("Initiator Name")) {
						obtainedList.add(we.getText());

					}

				}
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String s : obtainedList) {
				sortedList.add(s);
			}
			Collections.sort(sortedList);
		

			Assert.assertTrue(sortedList.equals(obtainedList), "columns sort");
		} else {
			Assert.assertTrue(false, "columns sort");
		}
	}

	public void verifyDescending() throws InterruptedException {
		colmunIndicator.click();
		Thread.sleep(500);
		ArrayList<String> obtainedList = new ArrayList<>();
		if (colIndicator.size() > 0) {

			for (WebElement we : colIndicator) {
				if (!we.getText().trim().equalsIgnoreCase("Initiator Name")) {
					obtainedList.add(we.getText());

				}
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String s : obtainedList) {
				sortedList.add(s);
			}
			Collections.sort(sortedList);
			Collections.reverse(sortedList);
			Assert.assertTrue(sortedList.equals(obtainedList), "columns sort Descending");
		} else {
			Assert.assertTrue(false, "columns sort");
		}
	}

}
