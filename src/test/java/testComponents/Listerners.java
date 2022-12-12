package testComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Abhay.resources.ExtentReporter;

public class Listerners extends BaseTest implements ITestListener{
	ExtentReports extent=ExtentReporter.toGetExtentReport(); 
	ExtentTest test;
	ThreadLocal<ExtentTest> extentLocal=new ThreadLocal<ExtentTest>(); //ThreadSafe
	
	@Override
	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		extentLocal.set(test); // Unique Thread ID
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentLocal.get().log(Status.PASS, "Test is Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentLocal.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filePath=null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	

	
	@Override
	public void onFinish(ITestContext context) {
     extent.flush();
	}
	
	

}
