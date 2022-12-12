package OIMT.Project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String productName="ADIDAS ORIGINAL";
	@Test(dataProvider="getData")
	public void login(HashMap<String,String> input) throws IOException
	{
		
		CatalogItem ct=lp.login(input.get("email"),input.get("password"));
		List<WebElement> products=ct.getProducts();
		ct.addProductToCart(productName);
		CartProduct cp=ct.goToCartPage();
		Boolean match=cp.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		PlaceOrder po=cp.checkOut();
		po.SelectCuntry("India");
		ConfirmationPageOrder cpo=po.submitOrder();
		String confirmation=cpo.verifyConfirmationMessage();
		Assert.assertEquals(confirmation, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"login"})
	public void OrderHistoryTest()
	{
		CatalogItem ct=lp.login("anshika@gmail.com","Iamking@000");
		OrderPage OP=ct.goToOrdersPage();
		Assert.assertTrue(OP.VerifyOrderDisplay(productName));
		
	}

	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Abhay//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	/*@DataProvider
	public void getData()
	{
		return new Object[][] {{"anshika@gmail.com","Iamking@000"},{"akgoel476@gmail.com","Badrinath@2022"}};
	}*/
	
	//HashMap<String,String> map=new HashMap<String,String>();
			//map.put("email","anshika@gmail.com");
			//map.put("password","Iamking@000");
			
			//HashMap<String,String> map1=new HashMap<String,String>();
			//map1.put("email","akgoel476@gmail.com");
			//map1.put("password","Iamking@000");
}
