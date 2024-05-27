package Selenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
    public LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		
		//Set global parameters using maven commands. Here we are using ternary operators of java. If System.getProperty("browser")!=null ?, it means if browser property at system level(System level property is property given through system using cmd through maven commands) is not null, then take the value of browser from browser attribute given at cmd which is mentioned in the 2nd argument(firefox) after ? and if it is null(or -D is not given in cmd), then picl local global variable browser attribute value present in properties file which is chrome
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
				
		//String browserName = prop.getProperty("browser");//System.getProperty("user.dir"): To get path till project seleniumFramework
		
		if(browserName.contains("chrome"))
		{
			 ChromeOptions options = new ChromeOptions();
			 if(browserName.contains("headless")) {
			 options.addArguments("headless");
			 driver= new ChromeDriver(options);
			
		}
		
			 driver= new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to Hashmap - download jackson databind library from maven
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
		}
	
	//new TypeReference<List<HashMap<String, String>>- It returns all the maps(which we want to pass as data in the test case in the form of list
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;//cast driver into TakesScreenshot as driver has info about browser and it takes screenshot when TC fails
		File source = ts.getScreenshotAs(OutputType.FILE);//to take screesnhot from browser into a file
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);// copy file from browser into file path mentioned in file so that it stores file on loacal
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";// return file path
		
	}
			
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver=initializeDriver();
		loginPage=new LoginPage(driver);
        loginPage.goTo();
        return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
