package com.vtiger.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.common.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;
	public static Map<String,Map<String,String>> alldata;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeSuite
	public void setup()
	{
		alldata=readExcelData();
		prop=readconfig();
		createReport();
		luanchApp();
	}
	
	
	public void luanchApp()
	{
		if(prop.getProperty("Browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else
		{	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
	     
		int ts = Integer.parseInt(prop.getProperty("Impliciwait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ts));
	}
	
	@AfterSuite
	public void closeApp()
	{
		driver.quit();
	}
	
	public Properties readconfig()
	{
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/setting/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public void createReport() 
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+str+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
	}
	
	public Map<String,Map<String,String>> readExcelData()
	{
		Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx");
		int rowcount = xr.getRowCount("Sheet1");
		int colmcount = xr.getColumnCount("Sheet1");
		Map<String,Map<String,String>> Alldata = new HashMap<String,Map<String,String>>(); 
		Map<String,String> rowdata;
		
		for(int i=2;i<=rowcount;i++)
		{
			rowdata = new HashMap<String,String>();
			for(int j=1;j<=colmcount-1;j++)
			{
				String key = xr.getCellData("Sheet1", j, 1);
				String value = xr.getCellData("Sheet1", key, i);
				rowdata.put(key, value);
				
			}
			
			Alldata.put(xr.getCellData("Sheet1", 0, i), rowdata);
		
		}
		
		return Alldata;
	}

}
