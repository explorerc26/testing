package com.samples.selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class PrintScreen {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

	public static void main(String[] args) throws Exception {

		WebDriver driver = getIEDriver();
		driver.get("http://www.google.com");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\test\\screenshot.png"));
		//printScreen(driver, "sample");
	}
	
	public static WebDriver getIEDriver(){
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		System.setProperty("webdriver.ie.driver", "C://selenium//32//IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(caps);
		return driver; 
	}
	
	public static WebDriver getChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver; 
	}
	
	public static WebDriver getHeadLessDriver(){
		return null; 
	}

	public static void printScreen(WebDriver driver, String policy) {

		LocalDateTime currentTime = LocalDateTime.now();
		String formattedDateTime = currentTime.format(formatter);
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(3000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG",
					new File("c:\\op\\test" + policy + "-" + formattedDateTime + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
