package com.demo.Testcases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.PageObject.BaseClass;
import com.demo.PageObject.LoginPage;



public class LoginTest extends BaseClass {
	
	@Test
	public void loginTest() throws IOException{
		
		
		LoginPage lp = new LoginPage(driver);
		logger.info("Entering the Username");
		lp.setUsername(username);
		logger.info("Entering the Password");

		lp.setPassword(password);
		
		lp.clickSubmit();
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
			
			Assert.assertTrue(true);
			logger.info("Login test Passed");
				}
		
		else
			
		{
			captureScreen(driver,"LoginTest");
			Assert.assertTrue(false);
			logger.info("Login Test Failed");

		}
		
	}

}
