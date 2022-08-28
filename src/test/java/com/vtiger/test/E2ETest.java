package com.vtiger.test;

import org.testng.annotations.Test;

import com.vtiger.pages.AccountPage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class E2ETest extends BaseTest {
	
	@Test
	public void verify_accountcreation_with_mandatory_fields_TC04()
	{
		String TCname="verify_accountcreation_with_mandatory_fields_TC04";
		logger = extent.createTest(TCname);
		LoginPage lp = new LoginPage(driver, logger);
		lp.login(alldata.get(TCname).get("userid"), alldata.get(TCname).get("Password"));
		LeadPage ldp = new LeadPage(driver, logger);
		ldp.clickNewLead();
		ldp.createLeadwithMandatoryFields(alldata.get(TCname).get("lname"), alldata.get(TCname).get("company"));
		ldp.ckickNewAccount();
		AccountPage ac = new AccountPage(driver, logger);
		ac.createAccountwithMandatoryFields(alldata.get(TCname).get("AccountName"));
		ldp.ckickLogout();
		extent.flush();
	}

}
