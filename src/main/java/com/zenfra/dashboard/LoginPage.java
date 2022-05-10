package com.zenfra.dashboard;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zenfra.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//*[@id='userName']")
	WebElement userName;
	
	@FindBy(xpath="//*[@id='password']")
	WebElement password;
	

	@FindBy(xpath="//button[@class='login100-form-btn']")
	WebElement loginBtn;
	
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public AzenfraHome Login(String un, String pwd) throws InterruptedException {
		userName.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(3000);
		loginBtn.click();
		
		Thread.sleep(3000);
		String parent=driver.getWindowHandle();
		System.out.println(parent);
		Thread.sleep(3000);
		Thread.sleep(3000);
		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		
		
		while(I1.hasNext())
		{
		
		String child_window=I1.next();
		System.out.println(child_window);

		if(!parent.equals(child_window))
		{
			driver.switchTo().window(child_window);

		System.out.println(driver.switchTo().window(child_window).getTitle());
		}
		
		}
		
		
		Thread.sleep(3000);
		
		return new AzenfraHome();
	}

}
