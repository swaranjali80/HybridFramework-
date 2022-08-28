package com.vtiger.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

public class CommonMethods {
	private WebDriver driver;
	public ExtentTest logger;
	public CommonMethods(ExtentTest logger,WebDriver driver)
	{
		this.logger=logger;
		this.driver=driver;
	}
	
	public void EnterValue(WebElement elm, String value, String elmName)
	{
		try
		{
		elm.clear();
		elm.sendKeys(value);
		logger.pass(value+ "has been entered successfully " +elmName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fail(value+ "did not enter into " +elmName+ "due to error "+e.getMessage()+"<span class='label end-time'><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public void SelectVisibleText(WebElement elm, String value, String elmName)
	{
		try
		{
		Select s = new Select(elm);
		s.selectByVisibleText(value);
		elm.sendKeys(value);
		logger.pass(value+ "has been selected from dropdown " +elmName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fail(value+ "did not select from dropdown " +elmName+ "due to error "+e.getMessage()+"<span class='label end-time'><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public void ClickElement(WebElement elm, String elmName)
	{
		try
		{
		elm.click();
		logger.pass(elmName+"has been clicked successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fail(elmName+"did not clic due to error "+e.getMessage()+"<span class='label end-time'><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public boolean CheckDisplay(WebElement elm, String elmName)
	{
		boolean b=false;
		try
		{
		b = elm.isDisplayed();
		logger.pass(elmName+"was displaying on the screen");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fail(elmName+"did not display on the screen due to error"+e.getMessage()+"<span class='label end-time'><a href='"+getscreenshot()+"'>Screenshot</a></span>");
			
		}
		return b;
	}
	      
	    public String getscreenshot()
	    {
	        DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
	        Date d = new Date();
	        String str = f.format(d);
	        TakesScreenshot scrShot = ((TakesScreenshot)driver);
	        //Call getScreenshotAs method to create image file
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        String path = System.getProperty(("user.dir")+"/src/test/java/com/vtiger/reports/screenshots/image"+str+" .png");
            //Move image file to new destination
            File DestFile=new File(path);
            //Copy file at destination
	        try {
	           FileUtils.copyFile(SrcFile, DestFile);
	        }
	        catch (Exception e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();
	        }
	        return path;

}
}
