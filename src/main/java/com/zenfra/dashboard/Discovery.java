package com.zenfra.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	// @FindBy(xpath = "//a[text()='VMAX Server']")
	@FindBy(xpath = "(//a[@class='un-selected-favview'])[1]")
	WebElement catergortyReport;

	@FindBy(xpath = "//a[@class='selected-favview']")
	WebElement selectedReport;

	@FindBy(xpath = "//div[@class='report_grid_data ']")
	WebElement discoveryReport;

	@FindBys(@FindBy(xpath = "//div[@aria-colindex='1' and contains(@class, 'ag-cell-last-left-pinned')]"))
	List<WebElement> reportPinnedElements;

//	@FindBys(@FindBy(xpath = "//div[@role='row']/div[@col-id='Initiator Name']"))
	@FindBys(@FindBy(xpath = "//div[@role='row']/div[@col-id='Host Group']"))
	List<WebElement> sortColumn;

	// @FindBy(xpath = "//span[@class='ag-header-cell-text' and text()='Initiator
	// Name' and @ref='eText']")
	@FindBy(xpath = "(//span[@class='ag-header-cell-text' and @ref='eText'])[3]")
	WebElement secondColmun;

	@FindBy(xpath = "(//input[@class='ag-input-field-input ag-text-field-input'])[3]")
	WebElement filerColmun;

	@FindBy(xpath = "//div[@aria-rowindex='3']/div[@aria-colindex='1' and text()='Total']")
	WebElement totalColmun;

	@FindBy(xpath = "//span[@class='ag-side-button-label' and text()='Columns']")
	WebElement hideColumn;

	@FindBys(@FindBy(xpath = "//div[@ref='cbSelect']"))
	List<WebElement> colmunsList;

	@FindBys(@FindBy(xpath = "//div[@class='ag-column-select-column ag-column-select-indent-0']/span[@class='ag-column-select-column-label']"))
	List<WebElement> colmunsValues;

	@FindBy(id = "excelExport")
	WebElement excelExport;

	@FindBys(@FindBy(xpath = "//button[@class='btn btn-primary']"))
	List<WebElement> excelExportOption;
	
	@FindBy(xpath = "//div[@class='s-alert-box-inner']")
	WebElement messageBox;

	public Discovery() {
		PageFactory.initElements(driver, this);
		testUtilObject = new TestUtil();
	}

	public void navigateToAnalyticsReport() throws InterruptedException {
		migraton.click();
		discocvery.click();
		testUtilObject.waitforLoadingIcon();
		testUtilObject.waitforElementClickable(catergortyReport);
		Thread.sleep(500);
		catergortyReport.click();
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

	public void verifyTotalColmun() {
		testUtilObject.waitforElementClickable(totalColmun);
		if (totalColmun.isDisplayed()) {
			Assert.assertTrue(true, "Report first first clomun is reserved for total");
		} else {
			Assert.assertTrue(false, "Report first first clomun is reserved for total");
		}
	}

	public void verifyAscending() throws InterruptedException {
		secondColmun.click();
		Thread.sleep(500);
		ArrayList<String> obtainedList = new ArrayList<>();
		if (sortColumn.size() > 0) {

			for (WebElement we : sortColumn) {
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase("Host Group")) {
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
		secondColmun.click();
		Thread.sleep(500);
		ArrayList<String> obtainedList = new ArrayList<>();
		if (sortColumn.size() > 0) {

			for (WebElement we : sortColumn) {
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase("Host Group")) {
						obtainedList.add(we.getText());
					}
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

	public void filerAndVerifyRecord() throws InterruptedException {
		filerColmun.sendKeys("rip00");
		Thread.sleep(500);
		List<WebElement> filteredColumn = driver.findElements(By.xpath("//div[@role='row']/div[@col-id='Host Group']"));
		ArrayList<String> filteredList = new ArrayList<>();
		if (filteredColumn.size() > 0) {

			for (int i = 1; i <= filteredColumn.size(); i++) {
				WebElement we = driver
						.findElement(By.xpath("(//div[@role='row']/div[@col-id='Host Group'])[" + i + "]"));
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase("Host Group")) {
						filteredList.add(we.getText());
					}
				}
			}
		}
		boolean matchfalg = false;
		for (String s : filteredList) {
			if (s.contains("rip00")) {
				matchfalg = true;
			} else {
				matchfalg = false;
			}

		}
		Assert.assertTrue(matchfalg, "verify filter records");
	}

	public void hideComuns() throws InterruptedException {
		String hiddenColmun = "";
		hideColumn.click();
		hiddenColmun = colmunsValues.get(0).getText().trim();
		colmunsList.get(0).click();
		if (driver.findElements(
				By.xpath("//div[@class='ag-header-cell-label']/span[@class='ag-header-cell-text' and text()='"
						+ hiddenColmun + "']"))
				.size() < 1) {

			Assert.assertTrue(true, "Selected Colmun " + hiddenColmun + " hidden");
			Thread.sleep(500);
			Actions act = new Actions(driver);
			act.moveToElement(selectedReport).click().build().perform();
			selectedReport.click();
			Thread.sleep(500);
			testUtilObject.waitforLoadingIcon();
			if (driver.findElements(
					By.xpath("//div[@class='ag-header-cell-label']/span[@class='ag-header-cell-text' and text()='"
							+ hiddenColmun + "']"))
					.size() < 1) {

				Assert.assertTrue(true, "Selected Colmun " + hiddenColmun + " hidden on relaod");
				colmunsList.get(0).click();
			} else

			{
				Assert.assertTrue(false, "Selected Colmun " + hiddenColmun + " hidden on relaod");

			}

		} else

		{
			Assert.assertTrue(false, "Selected Colmun " + hiddenColmun + " hidden");

		}
		hideColumn.click();

	}

	public void verifyExcelExportOptions() {
		excelExport.click();
		ArrayList<String> excelOptions = new ArrayList<>();
		for (WebElement we : excelExportOption) {
			excelOptions.add(we.getText().trim());
		}
		Assert.assertTrue(
				excelOptions.get(0).equalsIgnoreCase("All Rows All Columns")
						&& excelOptions.get(1).equalsIgnoreCase("All Rows Visible Columns")
						&& excelOptions.get(2).equalsIgnoreCase("Visible Rows All Columns")
						&& excelOptions.get(3).equalsIgnoreCase("Visible Rows Visible Columns"),
				"Excel Export Options");
	}
	
	public void downloadAsExcel() {
		excelExportOption.get(0).click();
		Assert.assertEquals("All Rows All Columns exported successfully", messageBox.getText());
	}

}
