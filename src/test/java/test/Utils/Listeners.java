package test.Utils;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import MobileAutomation.androidActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Listeners extends androidActions implements ITestListener { 
	// AndroidDriver driver; 
	 AppiumDriver driver ;

	public Listeners(AndroidDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
		// TODO Auto-generated constructor stub
	}
	ExtentTest test ;
	ExtentReports extent = extentReporter.getReportObject(); 
	@Override 
	public void onTestStart (ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName());
		
	}
	//@Override
	public void onTestSucess (ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		
		try {
				driver = (AppiumDriver) result.getClass().getField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
		        test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
		    } catch (Exception e) {
		        e.printStackTrace();
		    } 
		}
	
	@Override 
	public void onTestFailure(ITestResult result) {
	    test.fail(result.getThrowable()); 
	    try {
			driver = (AppiumDriver) result.getClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	        test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	}

	
	@Override 
	public void onTestSkipped (ITestResult result) {
		
		test.log(Status.SKIP, "Test Skipped");
	}
	
	@Override 
	public void onTestFailedButWithinSuccessPercentage (ITestResult result) {
		
	} 
	@Override 
	public void onStart(ITestContext context) {
		
	}
	@Override 

public void onFinish(ITestContext context) {
		extent.flush();
		
	} 
}
