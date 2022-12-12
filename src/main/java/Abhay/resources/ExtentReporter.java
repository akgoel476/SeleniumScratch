package Abhay.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporter {

	public static ExtentReports toGetExtentReport()
	{
		String Path=System.getProperty("user.dir")+"\\Reportee\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(Path);
		reporter.config().setReportName("Web Automation");
	    reporter.config().setDocumentTitle("Test Demo");
		
		ExtentReports extent=new ExtentReports();
		extent.setSystemInfo("Tester","Abhay Goel");
		return extent;
	}
}
