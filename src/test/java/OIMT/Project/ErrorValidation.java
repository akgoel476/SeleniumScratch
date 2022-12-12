package OIMT.Project;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;
import testComponents.RetryAnalyser;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"validation"}, retryAnalyzer=RetryAnalyser.class)
	public void Validatin()
	{
		lp.login("akgoel476@gmail.com","Iamking@000");
		Assert.assertEquals("Incorrect email or password.",lp.getErrorMessage());
	}
}
