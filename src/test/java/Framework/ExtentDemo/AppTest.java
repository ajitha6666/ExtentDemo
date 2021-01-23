package Framework.ExtentDemo;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.net.URL;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class AppTest
{
	WebDriver driver;
	Properties properties;
	ExtentReports report;
	ExtentTest test;
	public static final String USERNAME = "ajithaganapavara1";
	public static final String AUTOMATE_KEY = "g1CmjdNBnQZBfajLebyy";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	@BeforeSuite
	public void beforeSuite() throws IOException
	{
		String propertypath = System.getProperty("user.dir")+"\\Config.properties";
		File file = new File(propertypath);
		FileInputStream fi = new FileInputStream(file);
		//properties = new Properties();
		//properties.load(fi);
		
		String configFileName = "./src//main//resources//%s.properties";
        String EnvironmentName = System.getProperty("TestEnvironment");
        System.out.println("TestEnvironment: " + EnvironmentName);

        configFileName = String.format(configFileName, EnvironmentName);
        properties = new Properties();
        properties.load(new FileInputStream(new File(configFileName)));
		
		DesiredCapabilities caps = new DesiredCapabilities();
        
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("browser", "Firefox");
		caps.setCapability("browser_version", "83.0");
		caps.setCapability("name", "ajithaganapavara1's First Test");
		
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		test = report.startTest("AppTest");
		
		String chromepath = System.getProperty("user.dir")+"\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromepath);
		driver = new ChromeDriver();
		//driver = new RemoteWebDriver(new URL(URL),caps);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	public void launch()
	{
		//driver.get("https://www.expedia.com");
		System.out.println(properties.getProperty("url"));
		
		driver.get(properties.getProperty("url"));
	}
	@Test
	public void two()
	{
		int a=10;
		System.out.println("three");
		System.out.println("two");
	}
	@AfterSuite
	public void afterSuite()
	{

		report.endTest(test);
		report.flush();
		}
	
}
