package MobileAutomation;
import com.shaft.driver.SHAFT;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class pageShaftBase {
	  public static SHAFT.GUI.WebDriver driver;
	    public static WebDriverWait wait;
	    Actions action;
	    public String screenTitle;

	    public pageShaftBase(SHAFT.GUI.WebDriver driver) {
	    	pageShaftBase.driver = driver;
	    } 
	    
	    public void visibilityWaitForElementToBeClickable(By element) {
	        wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(100));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        wait.until(ExpectedConditions.visibilityOf(driver.getDriver().findElement(element)));
	    } 
	    public void visibilityWaitForElementLocated(By element) {
	        wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	    }
	    public void alertWait() {
	        wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.alertIsPresent());
	    } 	
	    public boolean checkElementIsDisplayed(By element) {
	        try {
	            visibilityWaitForElementLocated(element);
	            driver.getDriver().findElement(element).isDisplayed();
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
	    @Step
	    public void clickOnElement(By element) {
	        visibilityWaitForElementToBeClickable(element);
	        driver.getDriver().findElement(element).click();
	    } 
	    @Step("Click On The Element By Name Text")
	    public void clickElementByText(String text) {
	        clickOnElement(By.xpath("//*[@name= '" + text + "']"));
	    }

	    @Step("Click On The Element By Name Text")
	    public boolean checkElementIsDisplayedByText(String text) {
	        return checkElementIsDisplayed(By.xpath("//*[@name= '" + text + "']"));
	    }

	    @Step("Send Text")
	    public void sendText(By element, String text) {
	        visibilityWaitForElementToBeClickable(element);
	        driver.getDriver().findElement(element).click();
	        driver.getDriver().findElement(element).clear();
	        driver.getDriver().findElement(element).sendKeys(text);
	    } 
    @Step("Verify That The Correct Screen Is Opened Successfully ")
    public String checkTheRightScreenIsOpened(String ScreenTitle) {
	        screenTitle = getElementText(By.xpath("//XCUIElementTypeStaticText[@name= '" + ScreenTitle + "']"));
	        return screenTitle;
	    }  
	    @Step("User Can Scrolling to 'up,down,right,left'")
	    public pageShaftBase scrolling(String Scroll_Direction) {
	        HashMap<String, String> ScrollObject = new HashMap<>();
	        ScrollObject.put("direction", Scroll_Direction);
	        ((JavascriptExecutor) driver.getDriver()).executeScript("mobile:scroll", ScrollObject);
	        return this;
	    }  
	    @Step("Click on element to Scroll")
	    public void scrollAndClick(By ElementLocator, String Scroll_Direction) {
	        clickAndHold(ElementLocator);
	        scrolling(Scroll_Direction);
	    }
	    @Step("Click and Hold the Element Using Shaft")
	    public void clickAndHold(By ElementLocator) {
	        driver.element().clickAndHold(ElementLocator);
	    }

	    @Step("Get Element Text")
	    public String getElementText(By Ele) {
	        visibilityWaitForElementLocated(Ele);
	        return driver.getDriver().findElement(Ele).getText();
	    }

	    @Step("Send Text but by Action")
	    public void sendTextByAction(By Element, String Text) {
	        visibilityWaitForElementLocated(Element);
	        driver.getDriver().findElement(Element).click();
	        action = new Actions(driver.getDriver());
	        action.sendKeys(Text).perform();
	    }
	    @Step("Send Text but by Action And Clear The Existing Text")
	    public void sendTextByActionAndClearText(By Element, String Text) {
	        visibilityWaitForElementLocated(Element);
	        driver.getDriver().findElement(Element).click();
	        driver.getDriver().findElement(Element).clear();
	        action = new Actions(driver.getDriver());
	        action.sendKeys(Text).perform();
	    }

	    @Step("Accept All Alerts")
	    public void acceptAlerts() {
	        try {
	            driver.getDriver().switchTo().alert().accept();
	            alertWait();
	            driver.getDriver().switchTo().alert().accept();
	        } catch (Exception ignored) {
	        }
	    }

	    @Step("Go back")
	    public void goBack(int iterations) throws InterruptedException {
	        for (int i = 1; i <= iterations; i++) {
            (driver).getDriver().navigate().back();
            Thread.sleep(1000);
	        }
	    }

	    @Step("Click on Element to Hide The KeyBoard")
	    public void clickOnElementToHideKeyboard(By Element) {
	        driver.getDriver().findElement(Element).click();
	    } 
	    
	    @Step("Click on Element Using Shaft")
	    public void clickElement(By ElementLocator) {
	        driver.getDriver().findElement(ElementLocator).click();
	    }  
	
	   
}
