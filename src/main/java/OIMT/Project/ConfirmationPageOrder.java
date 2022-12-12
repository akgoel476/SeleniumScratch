package OIMT.Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPageOrder extends AbstractComponent{
	
WebDriver driver;
	
	public ConfirmationPageOrder(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirm;
	
	public String verifyConfirmationMessage()
	{
		return confirm.getText();
		
	}

}
