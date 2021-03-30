package com.inetbanking.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.Utilities.XLUtils;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	
	public void loginDDT(String username,String password){
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("username provided");
		
		lp.setPassword(password);
		logger.info("password provided");
		lp.clickSubmit();
		
		if(isAlertPresent()==true){
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Falied");
		}
		else{
			Assert.assertTrue(true);
			logger.info("Login Passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent(){
		
		try{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e){
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"test/java/com/inetbankingTestData/internetbanking.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int cellnum = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rownum][cellnum];
		
		for(int i=1;i<=rownum;i++){
			
			for(int j=0;j<cellnum;j++){
				
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i,j);
			}
		}
		
		return loginData;
	}
}
