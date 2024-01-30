package MobileAutomation;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
public class appiumBasics extends BaseTest { 

	@Test
	public void Click_App_button() throws InterruptedException {
		//				By appButton = AppiumBy.accessibilityId("App");   
		//this.driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("Test@gmail.com"); 
		this.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"standard_user\"));")).click();		 		
		//executeScroll1("text", "standard_user");
		
		Thread.sleep(1000);
		this.driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();  
		Thread.sleep(2000);  
		
	}  
	
	
}
