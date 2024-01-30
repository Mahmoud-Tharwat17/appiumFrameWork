package MobileAutomation;

import org.testng.annotations.AfterClass;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.awaitility.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.epam.healenium.treecomparing.Path;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;


import com.shaft.driver.SHAFT;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest { 

	public AndroidDriver driver;  
	public  AppiumDriverLocalService service;

	// public AndroidDriver driver;
	public SoftAssert softAssertion; 

	public List <HashMap<String , String>>getJsonData(String jsonFilePath)throws IOException {    

		String filePath = System.getProperty("user.dir") + "//src//test//java//testData//e-commerceData.json";
		File jsonFile = new File(filePath);
		String charsetName = "UTF-8"; // Change to the appropriate charset if needed

		String jsonContent = FileUtils.readFileToString(jsonFile, Charset.forName(charsetName));

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String ,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String , String>>>(){
		});
		return data ;

	}

	
	@SuppressWarnings("deprecation")
	@BeforeClass (alwaysRun = true)
	
	public  void setUp () throws IOException{ 
		Properties prop = new Properties (); 
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
		prop.load(fis); 
		String ipAddress = prop.getProperty("ipAddress"); 
		String portValue = prop.getProperty("port"); 
		int port = Integer.parseInt(portValue);
		//Start Appium Programmatically
		this.service = new AppiumServiceBuilder() .withIPAddress(ipAddress).usingPort(port).build();
		this.service.start();
		// Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName","Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); 
		// General Store 
		//		caps.setCapability("app",System.getProperty("user.dir")+"/apps/General-Store.apk"); 		
		//		caps.setCapability("appActivity", "com.androidsample.generalstore.MainActivity");
		//		caps.setCapability("appPackage", "com.androidsample.generalstore"); 	
		//	SwagLAB
		caps.setCapability("app",System.getProperty("user.dir")+"/apps/SwagLab.apk");
		caps.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "/apps/chromeDriver.exe"); // to start chrome driver
		caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		caps.setCapability("appPackage", "com.swaglabsmobileapp");
		System.out.println("Your message or text here 6");
		this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);   
	}    
	
	@Step("Open Screen Recording")
	private void openScreenRecorder() {
		driver.startRecordingScreen();
	//	  ((CanRecordScreen)this.driver).startRecordingScreen((new AndroidStartScreenRecordingOptions().withVideoSize("320:-2").withTimeLimit(Duration.ofMinutes(10L)))); 
	} 
	
	@AfterClass (alwaysRun = true)
	public void Teardown() throws IOException {
		//       this.closeScreenRecorder();
		this.driver.stopRecordingScreen();
		this.driver.quit();
		this.service.stop();
	}



}
