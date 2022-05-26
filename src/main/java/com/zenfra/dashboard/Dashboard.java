package com.zenfra.dashboard;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zenfra.base.TestBase;
import com.zenfra.util.TestUtil;

public class Dashboard extends TestBase {
	TestUtil testUtilObject;
	
	// Page Factory - OR:

	@FindBy(xpath = "//*[@id='logo']")
	WebElement logo;

	@FindBy(xpath = "//button[text()='Add Chart ']")
	WebElement addChart;

	@FindBy(xpath = "//span[text()='Discovery']")
	WebElement analyticsTypediscovery;

	@FindBy(xpath = "//span[text()='All']")
	WebElement categoryAll;

	//@FindBy(xpath = "//span[text()='Linux HBA']")
	@FindBy(xpath = "//span[text()='VMAX Server']")
	WebElement favoriteViewLinuxHBA;

	@FindBy(xpath = "//span[text()='Server vs OS version']")
	WebElement chartServervsOSversion;
	
	@FindBy(id = "check-al")
	WebElement chartAll;

	@FindBy(xpath = "//button[@class='btn btn-success']")
	WebElement addToDashboard;

	@FindBy(xpath = "//button[@class='btn btn-danger']")
	WebElement addToChartCancel;

	@FindBy(xpath = "//*[text()='Chart Added to Dashboard Successfully']")
	WebElement addMsg;

	@FindBy(xpath = "//*[text()='Chart Removed from Dashboard Successfully']")
	WebElement removeMsg;

	@FindBy(xpath = "//button[@class='btn btn-secondary btn-medium radius-btn']")
	WebElement removeChart;
	
	@FindBy(xpath = "//button[@class='export-btn pdf-icon btn dash-pdf-icon']")
	WebElement exportToPDF;
	@FindBy(xpath = "//button[@class='export-btn btn dash-pdf-icon']")
	WebElement exportToPPTX;
	
	@FindBy(xpath = "//div[@class='s-alert-box-inner']")
	WebElement messageBox;
	
	@FindBy(xpath = "//a[@href='/dashboard']")
	WebElement dashBoard;
	
	@FindBys(@FindBy(xpath="//a[@href='/dashboard']"))
	List<WebElement> dashBoardElements;
	
	@FindBy(className="addwidget-btn-right")
	WebElement loadingWidget;
	

	@FindBy(xpath = "//div[contains(text(), 'is loading...')]")
	WebElement loadingIcon;
	By xpathloadingIcon = By.xpath("//div[contains(text(), 'is loading...')]");
	

	public Dashboard() {
		PageFactory.initElements(driver, this);
		 testUtilObject = new TestUtil();
	}

	public void navigateToDashboard() {
		if (dashBoardElements.size() > 0 ) {
			logo.click();
		}
	}

	public void addChart() throws InterruptedException {
		testUtilObject.waitforLoadingIcon();
		logo.click();
		testUtilObject.waitforLoadingIcon();
		addChart.click();
		analyticsTypediscovery.click();
		categoryAll.click();
		favoriteViewLinuxHBA.click();
		//chartServervsOSversion.click();
		chartAll.click();
		addToDashboard.click();

		String success = addMsg.getText();
		System.out.println(success);
		Assert.assertEquals("Chart Added to Dashboard Successfully", success);

	}

	public void removeChart() throws InterruptedException {
		logo.click();
		removeChart.click();
		String success = removeMsg.getText();
		System.out.println(success);
		Assert.assertEquals("Chart Removed from Dashboard Successfully", success);

	}

	public void addChartCancelButton() throws InterruptedException {
		testUtilObject.waitforLoadingIcon();
		logo.click();
		testUtilObject.waitforLoadingIcon();
		testUtilObject.waitforElementClickable(addChart);
		addChart.click();
		analyticsTypediscovery.click();
		categoryAll.click();
		favoriteViewLinuxHBA.click();
		//chartServervsOSversion.click();
		addToChartCancel.click();

	}

	public void exportTOPDF() throws InterruptedException {
		testUtilObject.waitforLoadingIcon();
		testUtilObject.waitforElementClickable(exportToPDF);
		exportToPDF.click();
		//testUtilObject.waitforElementDisappear(loadingWidget);
		Assert.assertEquals("PDF exported successfully", messageBox.getText());
		testUtilObject.waitforElementDisappear(messageBox);
	}
	public void exportTOPPTX() throws InterruptedException {
		testUtilObject.waitforLoadingIcon();
		testUtilObject.waitforElementClickable(exportToPPTX);
		exportToPPTX.click();
		//testUtilObject.waitforElementDisappear(loadingWidget);
		Assert.assertEquals("PPTX exported successfully", messageBox.getText());
		testUtilObject.waitforElementDisappear(messageBox);
	}

}
