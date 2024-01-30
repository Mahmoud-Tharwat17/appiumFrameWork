package MobileAutomation;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public class generalStore extends androidActions {
	AndroidDriver driver; 


	public generalStore(AndroidDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
		// TODO Auto-generated constructor stub
	}

	public  AppiumDriverLocalService service;
	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5)); 


	By yourName = AppiumBy.id("com.androidsample.generalstore:id/nameField");
	By selectCountry = By.id("android:id/text1"); 
	By targetCountry= By.xpath("//android.widget.TextView[@text='Egypt']");
	By radioButton = By.xpath("//android.widget.RadioButton[@text='Female']");
	By LetsShop = By.id("com.androidsample.generalstore:id/btnLetsShop");
	By pageHeader= By.id("com.androidsample.generalstore:id/header");
	By toastText = By.xpath("(//android.widget.Toast)[1]"); 


	public generalStore enterYourName (String name) {
		sendText(yourName,name);
		driver.hideKeyboard();

		return this;
	} 

	public  generalStore scrollToCountry (String country) {
		clickOnElement(selectCountry);
		executeScroll(country);
		clickOnElement(targetCountry);
		return this;
	}  

	public generalStore selectType () {
		selectCheckBoxOrRadioButton(radioButton);
		return this;
	}
	public generalStore clickLetsShop () {
		clickOnElement(LetsShop);
		return this;
	}

	public String getToastText () {

		String textValue =  getText(LetsShop);
		return textValue;
	} 
	public String validatePageHeader (String attribute , String value) {

		wait.until(ExpectedConditions.attributeContains(pageHeader, attribute ,value));

		String pageHeaderName =  getText(pageHeader);
		return pageHeaderName ; 

	}


}
