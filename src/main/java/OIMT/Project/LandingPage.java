package OIMT.Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement user;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement login;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorCode;
	
	public CatalogItem login(String name, String password) 
	{
		user.sendKeys(name);
		Password.sendKeys(password);
		login.submit();
		CatalogItem ct=new CatalogItem(driver);
		return ct;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorCode);
		return errorCode.getText();
	}

}
