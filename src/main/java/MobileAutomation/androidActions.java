package MobileAutomation;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
public class androidActions { 

	AndroidDriver driver ;
	Actions action;
	public String screenTitle;
	public static WebDriverWait wait;


	public androidActions (AndroidDriver driver) {
		this.driver = driver ; 
		PageFactory.initElements(new AppiumFieldDecorator (driver) , this);
	}


	public String getText ( By ele) {
		visibilityWaitForElementLocated(ele);
		String text = driver.findElement(((By) ele)).getText();

		return text;
	} 

	public void executeScroll (String text ) {
		this.driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"));")).click(); 
	} 

	public void clickOnElement(By element) {
		this.visibilityWaitForElementToBeClickable(element);
		driver.findElement(element).click();
	} 

	@Step("Click On The Element By Name Text")
	public void clickElementByText(String text) {
		this.clickOnElement(By.xpath("//*[@name= '" + text + "']"));
	}

	@Step("Click On The Element By Name Text")
	public boolean checkElementIsDisplayedByText(String text) {
		return this.checkElementIsDisplayed(By.xpath("//*[@name= '" + text + "']"));
	}

	@Step("Send Text")
	public void sendText(By element, String text) {
		this.visibilityWaitForElementToBeClickable(element);
		driver.findElement(element).click();
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(new CharSequence[]{text});
	}
	public void Swipe  (WebElement firstImage , String direction) {

		boolean canSWipeMore;  
		do
		{ 
			canSWipeMore = (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId", ((RemoteWebElement)firstImage).getId(),
					"left", 100 ,"top", 100, "width",200,"height",200,  
					"direction",direction,
					"percen",3.0 
					)); 

		} while (canSWipeMore) ;
	}

	public void clipboardAction (String x , By ele) {

		driver.setClipboardText(x); 
		driver.findElement(ele).sendKeys(driver.getClipboardText());
	}
	public void dragAndDrop  (WebElement ele , int X , int Y) {
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
				"endX", X , 
				"endY", Y 
				)); 
	} 

	public void alertWait() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
	} 	

	@Step("Wait For Element To Be Clickable And Visible")
	public void visibilityWaitForElementToBeClickable(By element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100L));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
	}

	@Step("Wait For Element To Be Located")
	public void visibilityWaitForElementLocated(By element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	public void acceptAlerts() {
		try {
			driver.switchTo().alert().accept();
			alertWait();
			driver.switchTo().alert().accept();
		} catch (Exception ignored) {
		}

	} 

	@Step("Check Element Is Displayed")
	public boolean checkElementIsDisplayed(By element) {
		try {
			this.visibilityWaitForElementLocated(element);
			driver.findElement(element).isDisplayed();
			System.out.println("Element: " + element + " is Displayed");
			return true;
		} catch (NoSuchElementException var3) {
			System.out.println("Couldn't find element" + element);
			return false;
		} catch (TimeoutException var4) {
			System.out.println("timeout..." + element + " is not found");
			return false;
		}
	}


	@Step("User Can Scrolling to 'up,down,right,left'")
	public androidActions scrolling(String Scroll_Direction) {
		HashMap<String, String> ScrollObject = new HashMap();
		ScrollObject.put("direction", Scroll_Direction);
		((JavascriptExecutor)driver).executeScript("mobile:scroll", new Object[]{ScrollObject});
		return this;
	}
	@Step("Get Element Text")
	public String getElementText(By Ele) {
		this.visibilityWaitForElementLocated(Ele);
		return driver.findElement(Ele).getText();
	}

	@Step("Send Text but by Action")
	public void sendTextByAction(By Element, String Text) {
		this.visibilityWaitForElementLocated(Element);
		driver.findElement(Element).click();
		this.action = new Actions(driver);
		this.action.sendKeys(new CharSequence[]{Text}).perform();
	}

	@Step("Send Text but by Action And Clear The Existing Text")
	public void sendTextByActionAndClearText(By Element, String Text) {
		this.visibilityWaitForElementLocated(Element);
		driver.findElement(Element).click();
		driver.findElement(Element).clear();
		this.action = new Actions(driver);
		this.action.sendKeys(new CharSequence[]{Text}).perform();
	}


	public void longPress (WebElement ele) { 
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of( "elementId", ((RemoteWebElement) ele).getId(), 
						"duration",2000));
	} 
	public boolean checkElementIsSelected(By element) {
		try {
			driver.findElement(element).isSelected();
			System.out.println("Element: " + element + " is Displayed");
		} catch (NoSuchElementException noSuchElementException) {
			System.out.println("Couldn't find element" + element);
			return false;
		} catch (org.openqa.selenium.TimeoutException timeoutException) {
			System.out.println("timeout..." + element + " is not found");
			return false;
		}
		return true;
	} 

	public boolean checkElementIsEnabled(By element) {
		try {
			driver.findElement(element).isEnabled();
			System.out.println("Element: " + element + " is Enabled");

		} catch (NoSuchElementException noSuchElementException) {
			System.out.println("Couldn't find element" + element);
			return false;
		} catch (org.openqa.selenium.TimeoutException timeoutException) {
			System.out.println("timeout..." + element + " is not found");
			return false;
		}
		return true;
	} 
	public void selectCheckBoxOrRadioButton (By ele) {
		checkElementIsSelected( ele);		
		if (!checkElementIsSelected( ele)) {
			clickOnElement(ele);		
		} 
		else {
			System.out.println("Radio button is already selected. No need to click.");
		}
	} 
	public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//report"+testCaseName+".png";
		FileUtils.copyFile(source , new File (destinationFile));
		return destinationFile; 
		// 1- Capture and place in folder // 2- extent report pick file and attach to report
	}


}

