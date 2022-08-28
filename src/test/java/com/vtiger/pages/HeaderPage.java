package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.CommonMethods;

public class HeaderPage {
	
	
	private WebDriver driver;
	public CommonMethods cm;
	public ExtentTest logger;
	
	public HeaderPage(WebDriver driver,ExtentTest logger)
	{
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
		cm = new CommonMethods(logger,driver);
	}
	
	@FindBy(linkText="Logout")
	WebElement lnk_Logout;
	
	@FindBy(linkText="New Lead")
	WebElement lnk_Lead;
	
	@FindBy(linkText="Leads")
	WebElement lnk_NewLead;
	
	@FindBy(linkText="New Account")
	WebElement lnk_NewAccount;
	
	
	
	public void ckickLogout()
	{
		cm.ClickElement(lnk_Logout, "Link Logout");
	}
	
	public void ckickNewAccount()
	{
		cm.ClickElement(lnk_NewAccount, "Link New Account");
	}
	
	public void clickLeads()
	{
		cm.ClickElement(lnk_Lead, "Link Leads");
	}
	
	public void clickNewLead()
	{
		cm.ClickElement(lnk_NewLead, "Link New Lead");
	}
	

}
