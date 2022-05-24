package com.zenfra.dashboard;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zenfra.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//*[@id='userName']")
	WebElement userName;
	
	@FindBy(xpath="//*[@id='password']")
	WebElement password;
	@FindBy(id="errorMessage")
	WebElement login_error;
	
	@FindBy(xpath="//a[text()=' Forgot Password? ']")
	WebElement forgotPassword;

	@FindBy(xpath="//button[@class='login100-form-btn']")
	WebElement loginBtn;
	@FindBy(xpath="//input[@class='form-control palceTxt']")
	WebElement email;
	@FindBy(className="sendlink-btn")
	WebElement sendRestLink;
	@FindBy(className="s-alert-box-inner")
	WebElement innerErroMessage;
	
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public AzenfraHome Login(String un, String pwd) throws InterruptedException {
		userName.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(100);
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
	public void lockAccount(String un) {
		for (int i=0; i<=2; i++) {
			userName.sendKeys(un);
			password.sendKeys("Test_Lock");
			//Thread.sleep(3000);
			loginBtn.click();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ( login_error.getText().contains("Your account is locked") ){
				Assert.assertTrue(true);
				break;
			}
		}
	
	}
	public void naviagteToResetpassword() {
		forgotPassword.click();
		
	}
	public void verifyInvalidEmail() {
		email.clear();
		email.sendKeys("wrong@email.com");
		sendRestLink.click();
		Assert.assertEquals(innerErroMessage.getText(),"Please enter the valid email address");
	}
	public void verifyValidEmail(String user_email) {
		email.clear();
		email.sendKeys(user_email);
	}

}
