package MobileAutomation;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cartPage extends androidActions {
	AndroidDriver driver; 


	public cartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
		// TODO Auto-generated constructor stub
	}
	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5)); 

	@AndroidFindBy (id = "com.androidsample.generalstore:id/termsButton")
	public WebElement terms;

	By pageName = By.id("com.androidsample.generalstore:id/appbar_btn_cart"); 
	By cartValue = By.id("com.androidsample.generalstore:id/productName");  
	By productCosts = By.id("com.androidsample.generalstore:id/productPrice");
	By billValue = By.id("com.androidsample.generalstore:id/totalAmountLbl");

	By termsAndConditions = By.id("com.androidsample.generalstore:id/termsButton");
	By closeButton = By.id("android:id/button1");
	By checkBox = AppiumBy.className("android.widget.CheckBox");
	By proceedButton = By.id("com.androidsample.generalstore:id/btnProceed");

	public String getProductName (String value) {

		String productName = getText(billValue);
		return productName ;	
	}
	public Double totalPrice () {
		double totalSum = 0;
		List <WebElement> productPrices =  driver.findElements(cartValue);
		int count =  productPrices.size();
		for (int i =0 ; i<count ; i++) {
			String amountString = productPrices.get(i).getText();
			double price = Double.parseDouble(amountString.substring(1)); 
			totalSum = totalSum+price ; 

		}
		return totalSum;
	}

	public Double billValueAmount () {

		String amountString = 	getText(billValue);
		double totalBillValue = Double.parseDouble(amountString.substring(1)); 
		return totalBillValue ;

	} 

	public cartPage longPressOnTermsAndConditions () {
		longPress(terms);
		clickOnElement(closeButton);
		return this;
	}

	public cartPage checkTermsAndCondition () {

		if (checkElementIsEnabled(checkBox)) {
			clickOnElement(checkBox);	  
		} 
		else 
		{
			System.out.println("element is disabled");

		}
		return this;
	} 

	public cartPage Proceed() {
		clickOnElement(proceedButton);
		return this;
	} 

}
