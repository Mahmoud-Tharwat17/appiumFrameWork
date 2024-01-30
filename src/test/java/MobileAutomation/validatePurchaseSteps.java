package MobileAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class validatePurchaseSteps  extends BaseTest{
	public void preSetup() {
		//Screen to cart page  in the second test the app will open on the home page here we cleaning the test app data not removing the app and start from the beginning 
		Activity activity = new Activity ("com.androidsample.generalstore","com.androidsample.generalstore.CartActivity");
		driver.startActivity(activity);
	}
	
	AndroidDriver driver;
	generalStore Home = new generalStore(driver); 
	productsPage Products= new productsPage (driver);
	cartPage Cart= new cartPage(driver);
	webPage WebPAGE = new webPage(driver);
	 @Test(dataProvider = "getData", description = "Given I'm on the Cart page, I want to validate products price with total amount in the bill")

	public void validateTotalPurchaseAmount(String name , String country) throws InterruptedException { 
		Home.enterYourName(name).scrollToCountry(country)
		.selectType()
		.clickLetsShop();  

		Products.addProductToCart("Converse All Star").addProductToCart("PG 3").clickOnCartButton(); 
		Double productsCost= Cart.totalPrice(); 
		Double  billAmount = Cart.billValueAmount(); 
		Assert.assertEquals(productsCost, billAmount);
	}

	@Test (description =" Given I'm switched to the browser from App Validate When I press Back Button I back again to General Store page")
	public void validateCanAcceptsTermsAndConditions() throws InterruptedException { 
	
		Cart.longPressOnTermsAndConditions().checkTermsAndCondition().Proceed();	
		WebPAGE.searchForQuery("Zamalek 1911", "WEBVIEW").pressBack("NATIVE_APP");       
		String pageHeader = Home.validatePageHeader("text", "General Store"); 
		Assert.assertEquals(pageHeader, "General Store");
	}
	@DataProvider 
	public Object [][] getData () {
		return new Object [] [] {{"Tharwat","Egypt"}};
	}
}
