package test.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReporter {
	static ExtentReports extent ; 
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("");
		reporter.config().setDocumentTitle("");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name","Your Name");
		return extent; 
	}

}
