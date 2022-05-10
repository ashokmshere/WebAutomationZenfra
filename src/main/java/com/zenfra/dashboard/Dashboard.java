package com.zenfra.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zenfra.base.TestBase;

public class Dashboard extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//*[@id='logo']")
	WebElement logo;
	
	@FindBy(xpath="//*[@class='btn btn-success margin-right-15']")
	WebElement addChart;
	

	@FindBy(xpath="//span[text()='Discovery']")
	WebElement analyticsTypediscovery;
	
	@FindBy(xpath="//span[text()='All']")
	WebElement categoryAll;
	
	@FindBy(xpath="//span[text()='Linux HBA']")
	WebElement favoriteViewLinuxHBA;
	
	@FindBy(xpath="//span[text()='Server vs OS version']")
	WebElement chartServervsOSversion;
	
	@FindBy(xpath="//button[@class='btn btn-success']")
	WebElement addToDashboard;
	
	@FindBy(xpath="//button[@class='btn btn-danger']")
	WebElement addToChartCancel;
	
	
	@FindBy(xpath="//*[text()='Chart Added to Dashboard Successfully']")
	WebElement addMsg;
	
	@FindBy(xpath="//*[text()='Chart Removed from Dashboard Successfully']")
	WebElement removeMsg;
	
	@FindBy(xpath="//button[@class='btn btn-secondary btn-medium radius-btn']")
	WebElement removeChart;
	
	public Dashboard() {
		PageFactory.initElements(driver, this);
	}
	
	public void addChart() throws InterruptedException {
		logo.click();
		addChart.click();
		analyticsTypediscovery.click();
		categoryAll.click();
		favoriteViewLinuxHBA.click();
		chartServervsOSversion.click();
		addToDashboard.click();
		
		String success=addMsg.getText();
		System.out.println(success);
		Assert.assertEquals("Chart Added to Dashboard Successfully", success);
		
	}
	
	public void removeChart() throws InterruptedException {
		logo.click();
		removeChart.click();
		String success=removeMsg.getText();
		System.out.println(success);
		Assert.assertEquals("Chart Removed from Dashboard Successfully", success);
		
	}
	
	public void addChartCancelButton() throws InterruptedException {
		logo.click();
		addChart.click();
		analyticsTypediscovery.click();
		categoryAll.click();
		favoriteViewLinuxHBA.click();
		chartServervsOSversion.click();
		addToChartCancel.click();
		
		
		
	}


}