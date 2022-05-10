	package com.zenfra.dashboard;

import org.openqa.selenium.support.PageFactory;

import com.zenfra.base.TestBase;

public class AzenfraHome extends TestBase {
	
	
	public AzenfraHome() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle() {
		return driver.getTitle();
	}
	
		


}
