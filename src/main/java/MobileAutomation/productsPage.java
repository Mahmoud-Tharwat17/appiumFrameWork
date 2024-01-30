package MobileAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class productsPage extends androidActions {
	AndroidDriver driver; 

	public productsPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
		// TODO Auto-generated constructor stub
	}    
	By productCounts = By.id("com.androidsample.generalstore:id/productName");
	By addToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");
	By cartButton = By.id("com.androidsample.generalstore:id/appbar_btn_cart");


	public productsPage addProductToCart ( String value) {
		executeScroll(value);
		int productCount = driver.findElements(productCounts).size();
		for (int i=0 ; i<productCount ; i++)
		{

			String productName = driver.findElements(productCounts).get(i).getText(); 
			if(productName.equalsIgnoreCase(value)) {
				driver.findElements(addToCartButton).get(i).click();

				break;
			}
		}		
		return this;
	}  
	public productsPage clickOnCartButton () {
		clickOnElement(cartButton);
		return this;
	}

}
