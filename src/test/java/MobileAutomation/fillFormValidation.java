package MobileAutomation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class fillFormValidation extends BaseTest {
	@SuppressWarnings("deprecation")
	@BeforeMethod  (alwaysRun = true)
	public void preSetup() {
		//Screen to home page  in the second test the app will open on the home page here we cleaning the test app data not removing the app and start from the beginning 
		Activity activity = new Activity ("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		
	}
	AndroidDriver driver;
	generalStore Home = new generalStore(driver); 
	productsPage Products= new productsPage (driver);
	cartPage Cart= new cartPage(driver);

	@Test (description ="Given I am on the  Home page When I'm forget to enter my name I got a toast message informed", groups = {"Smoke"})
	public void validateTheNameIsMandatory() throws InterruptedException { 
		Home.enterYourName("").scrollToCountry("Egypt")
		.selectType().clickLetsShop(); 	
		String toastMessage = Home.getToastText();
		Assert.assertEquals(toastMessage, "Please enter your name");
	} 
	

	@Test (dataProvider = "getData", description =" Given I'm on the Home page I can Start Shopping When I fill all Data in the form", groups = {"Smoke"})
	public void validateUserCanStartShopping(HashMap<String,String>input) throws InterruptedException { 
		Home.enterYourName(input.get("name")).scrollToCountry(input.get("country")) 
		.selectType().clickLetsShop();  
		Products.addProductToCart("Converse All Star").clickOnCartButton(); 
		String productName = Cart.getProductName( "Converse All Star");
		Assert.assertEquals(productName, "Converse All Star");
	} 
	
	@DataProvider 
	public Object [] []getData()throws IOException {
		List <HashMap <String , String>> data = getJsonData(System.getProperty("user.dir")+"//Appium//src//test//java//testData//e-commerceData.json");
		return new Object [] [] {{ data.get(0)}, {data.get(1)}};
	}

}
