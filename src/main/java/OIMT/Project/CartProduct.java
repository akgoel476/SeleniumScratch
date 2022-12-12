package OIMT.Project;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartProduct extends AbstractComponent{
	WebDriver driver;
	
	public CartProduct(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(xpath="//li[@class='totalRow'][3]/button")
	WebElement checkout;
	
	public Boolean VerifyProductDisplay(String productName)
	{
	Boolean match=productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public PlaceOrder checkOut()
	{
		checkout.click();
		return new PlaceOrder(driver);
	}
	
	
}
