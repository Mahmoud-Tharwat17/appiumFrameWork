package MobileAutomation;

import org.testng.annotations.Test;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class eCommerce_Case_1 extends BaseTest{ 
	AndroidDriver driver;
	generalStore Home = new generalStore(driver); 
	productsPage Products= new productsPage (driver);
	cartPage Cart= new cartPage(driver);
	webPage WebPAGE = new webPage(driver);

	@Test (description ="Given I am on the  Home page When I'm forget to enter my name I got a toast message informed")
	public void validateTheNameIsMandatory() throws InterruptedException { 
		Home.enterYourName("").scrollToCountry("Egypt")
		.selectType().clickLetsShop(); 	
		String toastMessage = Home.getToastText(); 
		System.out.println(toastMessage);
		Assert.assertEquals(toastMessage, "Please enter your name");
		}

	@Test (description =" Given I'm on the Home page I can Start Shopping When I fill all Data in the form")
	public void validateUserCanStartShopping() throws InterruptedException { 
		Home.enterYourName("Tharwat").scrollToCountry("Egypt")
		.selectType().clickLetsShop();  

		Products.addProductToCart("Converse All Star").clickOnCartButton(); 
		String productName = Cart.getProductName( "Converse All Star");
		Assert.assertEquals(productName, "Converse All Star");
	}
	@Test (description =" Given I'm on the Cart page I want to validate products price with total amount in the bill")

	public void validateTotalPurchaseAmount() throws InterruptedException { 
		Home.enterYourName("Tharwat").scrollToCountry("Egypt")
		.selectType()
		.clickLetsShop();  

		Products.addProductToCart("Converse All Star").addProductToCart("PG 3").clickOnCartButton(); 
		Double productsCost= Cart.totalPrice(); 
		Double  billAmount = Cart.billValueAmount(); 
		System.out.println(billAmount);
		Assert.assertEquals(productsCost, billAmount);
	}

	@Test (description =" Given I'm switched to the browser from App Validate When I press Back Button I back again to General Store page")
	public void validateCanAcceptsTermsAndConditions() throws InterruptedException { 
		Home.enterYourName("Tharwat").scrollToCountry("Egypt")
		.selectType().clickLetsShop();  

		Products.addProductToCart("Converse All Star").addProductToCart("PG 3").clickOnCartButton(); 
		Double productsCost= Cart.totalPrice(); 
		Double  billAmount = Cart.billValueAmount();  
		System.out.println("The output is " +billAmount);

		Assert.assertEquals(productsCost, billAmount);

		Cart.longPressOnTermsAndConditions().checkTermsAndCondition().Proceed();	
		WebPAGE.searchForQuery("Zamalek 1911", "WEBVIEW").pressBack("NATIVE_APP");       
		String pageHeader = Home.validatePageHeader("text", "General Store");  
		System.out.println(pageHeader);
		Assert.assertEquals(pageHeader, "General Store");
	}

}
