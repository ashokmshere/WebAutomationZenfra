package com.zenfra.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	public String colName;
	List<WebElement> sortColumn;

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
//	@FindBys(@FindBy(xpath = "//div[@role='row']/div[@col-id='Host Group']"))
//	List<WebElement> sortColumn;

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

	@FindBy(xpath = "//button[text()='Add Chart  ']")
	WebElement addChart;

	@FindBy(xpath = "//div[@class='pull-right']/button[@class='btn btn-primary']")
	WebElement popupAddChart;

	@FindBys(@FindBy(xpath = "//label[text()='Chart Name']"))
	List<WebElement> labelChartName;

	@FindBy(xpath = "//input[contains(@class, 'react-inputs-validation__textbox__input')]")
	WebElement chartName;

	@FindBy(xpath = "//label[@for='xaxis']/following::div/div")
	WebElement selectXaxis;

	@FindBy(xpath = "//label[@for='yaxis']/following::div/*/div[contains(@class, 'react-select__placeholder')]")
	WebElement selectYaxis;

	@FindBy(xpath = "//div[text()='Serial Number']")
	WebElement serialNumber;

	@FindBy(xpath = "//label[@for='yaxis']/following::div/*/input[contains(@id, 'react-select-9-input')]")
	WebElement yAxisInput;

	@FindBy(xpath = "//h1[text()='Automation']/parent::div[@class='Layout-title']/following::div/button[@title='Close Chart']")
	WebElement deleteChart;

	@FindBy(xpath = "//h6[@class='list-group-item-heading' and text()='Automation']/parent::div")
	WebElement widgetChart;

	@FindBy(xpath = "//h6[@class='list-group-item-heading' and text()='Automation']")
	WebElement widgetChartText;

	@FindBy(xpath = "//h6[@class='list-group-item-heading' and text()='Automation']/parent::div/following::div/span")
	WebElement deleteWidget;

	@FindBy(xpath = "//button[text()='  Save & Close ']")
	WebElement save_close;

	@FindBy(xpath = "//button[text()='  Save & Close ']")
	WebElement cancelChart;

	@FindBy(xpath = "//button[@class = 'export-btn pdf-icon btn sub-rep-exp-sec']")
	WebElement exportPDF;

	@FindBy(xpath = "//button[@class = 'export-btn btn sub-rep-exp-sec']")
	WebElement exportPPT;

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
		String col_Name = getColmunName() ;
		sortColumn = driver.findElements(By.xpath("//div[@role='row']/div[@col-id='" + col_Name + "']"));

		ArrayList<String> obtainedList = new ArrayList<>();
		if (sortColumn.size() > 0) {

			for (WebElement we : sortColumn) {
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase(col_Name)) {
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
		String col_Name = getColmunName() ;
		sortColumn = driver.findElements(By.xpath("//div[@role='row']/div[@col-id='" + col_Name + "']"));

		ArrayList<String> obtainedList = new ArrayList<>();
		if (sortColumn.size() > 0) {

			for (WebElement we : sortColumn) {
				if (!we.getText().trim().equalsIgnoreCase("")) {
					if (!we.getText().trim().equalsIgnoreCase(col_Name)) {
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

	public void navigateToAddchartWindow() {
		addChart.click();
		Assert.assertTrue(driver.findElements(By.xpath("//label[text()='Pick a widget to add']")).size() > 0,
				"Add Chart popup");

	}

	public void AddchartWindow() throws InterruptedException {
		popupAddChart.click();
		Assert.assertTrue(labelChartName.size() > 0, "Add Chart Creation Window");
		chartName.sendKeys("Automation");
		selectXaxis.click();
		serialNumber.click();
		selectYaxis.click();
		Thread.sleep(500);
		yAxisInput.sendKeys("Host Group");
		yAxisInput.sendKeys(Keys.TAB);
		// hostGroup.click();
		Assert.assertTrue(driver.findElements(By.xpath("//div[@class='add-chart-disp']")).size() > 0,
				"Chart Display on the right side of window");
		// Assert.assertEquals("Chart Created Successfully",
		// messageBox.getText().trim());
		Thread.sleep(500);
		Assert.assertTrue(
				driver.findElements(By.xpath("//div[@class='Layout-title']/h1[text()='Automation']")).size() > 0,
				"added chart should be shown as last position");
		// save_close.click();
		cancelChart.click();
	}

	public void removeChart() throws InterruptedException {
		Thread.sleep(1000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)");
		testUtilObject.waitforElementClickable(deleteChart);
		deleteChart.click();
		Assert.assertTrue(
				driver.findElements(By.xpath("//div[@class='Layout-title']/h1[text()='Automation']")).size() <= 0,
				"added chart should be removed");
	}

	public void verifyPickWidget() {
		addChart.click();
		Assert.assertEquals("AUTOMATION", widgetChartText.getText().trim());

	}

	public void addChartFromWidget() {
		widgetChart.click();
		System.out
				.println(driver.findElements(By.xpath("//div[@class='Layout-title']/h1[text()='Automation']")).size());
		Assert.assertTrue(
				driver.findElements(By.xpath("//div[@class='Layout-title']/h1[text()='Automation']")).size() > 0,
				"added chart should be shown as last position");
		deleteChart.click();
		// addChart.click();
		// deleteWidget.click();

	}

	public void exportToPDF() {
		exportPDF.click();
		Assert.assertEquals("PDF exported successfully", messageBox.getText().trim());
		testUtilObject.waitforElementDisappear(messageBox);

	}

	public void exportToPPTX() {
		exportPPT.click();
		Assert.assertEquals("PPTX exported successfully", messageBox.getText().trim());
		testUtilObject.waitforElementDisappear(messageBox);

	}

	public String getColmunName() {
		hideColumn.click();
		String secondColmunName = driver.findElement(By.xpath("(//span[@class='ag-column-select-column-label'])[2]"))
				.getText();
		hideColumn.click();
		return secondColmunName;

	}
}
