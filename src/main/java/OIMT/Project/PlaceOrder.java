package OIMT.Project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder extends AbstractComponent{
	WebDriver driver;
	
	public PlaceOrder(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement NameOnCard;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By locate=By.cssSelector(".ta-results");
	
	
	public void SelectCuntry(String CountryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,CountryName).build().perform();
		waitForElementToAppear(locate);
		selectCountry.click();
	}
	
	public ConfirmationPageOrder submitOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,150)");
		NameOnCard.click();
	    return new ConfirmationPageOrder(driver);
	}

	
	
		
}
