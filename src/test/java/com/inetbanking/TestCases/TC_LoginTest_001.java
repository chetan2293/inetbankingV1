package com.inetbanking.TestCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest(){
		
		driver.get(baseURL);
		
		logger.info("URL is opened");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("GTPL Bank Manager HomePage")){
			Assert.assertTrue(true);
			logger.info("TC passed");
		}
		else{
			Assert.assertTrue(false);
			logger.info("TC Failed");
		}
	}
}
