package MobileAutomation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class fistTestCase {

	AppiumDriver driver;  
     AppiumDriverLocalService service;

	@BeforeMethod
	@BeforeTest
	public  void setUp () throws MalformedURLException{ 
		//Start Appium Programmatically
		service = new AppiumServiceBuilder()
			       .withIPAddress("127.0.0.1")
			       .usingPort(4723)
			       .build();
			service.start();
			// Desired Capabilities
	
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName","Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); 
		caps.setCapability("app",System.getProperty("user.dir")+"/apps/SwagLab.apk"); 

		caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		caps.setCapability("appPackage", "com.swaglabsmobileapp"); 
		System.out.println("Your message or text here 6");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);  
	
	} 
	@Test
	public void click_login() throws MalformedURLException, InterruptedException {
		Thread.sleep(1000);
		System.out.println("Your message or text here 7");  
		driver.findElement(AppiumBy.accessibilityId("test-Username")).click(); 
			driver.quit();
	}  
	@AfterMethod
	@AfterTest 
	public void tearDown() {
	if(null!=driver) {
			driver.quit();  
			service.stop();
		} 
	} 
}
