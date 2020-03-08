package com.demo.Testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.PageObject.BaseClass;
import com.demo.PageObject.LoginPage;
import com.demo.utilities.XLUtils;

public class TC_002_DDT_LoginPage extends BaseClass {

	@Test(dataProvider="LoginData")
	public void LoginDDT(String usr, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(usr);
		
		logger.info("User Name Provided");
		lp.setPassword(pwd);
		logger.info("Password has Entered");
		lp.clickSubmit();
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
		driver.switchTo().alert().accept(); //close the Alert
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.warn("Login Failed");
		}
		else {
			
			Assert.assertTrue(true);
			logger.info("Login Passed");
			Thread.sleep(3000);
			lp.Logout();
			driver.switchTo().alert().accept(); //Close the Logout Link
			driver.switchTo().defaultContent();
		}
}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {

			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/demo/Testdata/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String Logindata[][] = new String[rownum][colcount];
		for (int i = 0; i <= rownum; i++) {

			for (int j = 0; j <colcount; j++) {

				Logindata[i][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 
			}											

			}
		
		return Logindata;
	}

}
