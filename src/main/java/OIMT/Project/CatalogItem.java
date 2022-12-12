package OIMT.Project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogItem extends AbstractComponent {
	WebDriver driver;
	
	public CatalogItem(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="div[class*='mb-3']")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement ele;
	
	By products= By.cssSelector("div[class*='mb-3']");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProducts()
	{
		waitForElementToAppear(products);
		return Products;
	}
	
	public WebElement getProductByname(String product)
	{
		WebElement prod=getProducts().stream().filter(product1->product1.findElement(By.cssSelector("b")).getText().equals(product)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByname(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(ele);
	}
	
}
