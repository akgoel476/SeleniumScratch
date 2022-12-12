package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import OIMT.Project.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver intializeDriver() throws IOException
	{
		
		
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Abhay\\resources\\GlobalData.properties");
		prop.load(file);
		String BrowserName=prop.getProperty("browser");
		
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			//Firefox
		}
		
		else if(BrowserName.equalsIgnoreCase("edge"))
		{
			//Edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException
	{
		// read Json to String
		String JsonContent= FileUtils.readFileToString(new File(FilePath)
				,StandardCharsets.UTF_8);
	
	    // String to HashMap Jackson DataBind
		ObjectMapper mapper=new ObjectMapper();
		List <HashMap<String, String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchAppliaction() throws IOException
	{
		driver= intializeDriver();
		lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void close()
	{
		driver.close();
	}

}
