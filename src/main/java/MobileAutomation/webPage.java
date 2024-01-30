package MobileAutomation;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class webPage extends androidActions { 
	
	
		
	AndroidDriver driver; 

	public webPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
		// TODO Auto-generated constructor stub
	}
	// Assuming your 'driver' is an instance of WebDriver

	// Cast WebDriver to JavascriptExecutor

	// Now you can use jsExecutor to execute JavaScript commands

	By googleSearch = By.name("q");
	By navBar = By.xpath("//span[@class='navbar-toggler-icon']");
	By productButton = By.cssSelector("a[routerlink='/products']");
	By Devops = By.cssSelector("a[href='/products']");

	public webPage getName (String driverType) {
		int count = 0;
		Set <String> contexts = driver.getContextHandles();
		for (String contextName : contexts) {
		    System.out.println(contextName); 
		    if (driverType =="NATIVE_APP"&& count ==0) 
		    {   
		    	
			    System.out.println(contextName); 
		    	driver.context(contextName);
		    	break ;
		    }
		if (driverType == "WEBVIEW" && count ==1)
		{
			System.out.println(contextName); 
	    	driver.context(contextName);
	    	break ;

		} 
		count ++ ;
	}
		return this;
  }  
	public webPage searchForQuery(String query, String driverType) {
		getName(driverType);
		driver.findElement(googleSearch).sendKeys(query + Keys.ENTER);
		return this;
	}   
	public webPage pressBack( String driverType) {
		getName(driverType);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		return this;
	}   
	
	public webPage openWebPage () {
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		return this ;
		
	}
	public String getElementValue () {
		
	clickOnElement(navBar);
	clickOnElement(productButton);
	((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
	String Text = getText(Devops);
		return Text ;
	} 
	
	
}