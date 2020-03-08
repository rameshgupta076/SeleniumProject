package com.demo.PageObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.demo.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	// creating object of the ReadConfig class

	ReadConfig readconfig = new ReadConfig(); // To access the Methods of
												// Utilities Class
	public String baseURL = readconfig.getApplicationURL(); // getting value
															// from the
															// readconfig class
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters({"browser"})
	@BeforeClass
	public void setup(String br) {
		
		logger = Logger.getLogger("EBanking");
		// BasicConfigurator.configure("log4j.properties");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equalsIgnoreCase("firefox")) {

			 System.setProperty("webdriver.gecko.driver", readconfig.getfireFoxpath());
			driver = new FirefoxDriver();
			
			//WebDriverManager.firefoxdriver().setup();
		}
		
		else if(br.equalsIgnoreCase("chrome")){
			
			 System.setProperty("webdriver.gecko.driver", readconfig.getfireFoxpath());
				driver = new FirefoxDriver();
			
			//WebDriverManager.chromedriver().setup();
		}

		// C:/Users/admin/workspace/SeleniumProject/Drivers/geckodriver.exe

		driver.get(baseURL);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 File target = new File(System.getProperty("user.dir")+"/screenshots/"+tname+".png");
		 
		 FileUtils.copyFile(source, target);
		 System.out.println("Screenshot Taken");
		
	}

}
