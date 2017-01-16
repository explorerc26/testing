package com.samples.selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class SimplePrintScreen {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

	public static void main(String[] args) throws Exception {
		// http://www.seleniumhq.org/download/ take java jars include in driver
		// https://sites.google.com/a/chromium.org/chromedriver/downloads
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://sample url");
		printScreen(driver,"login");
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("un");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("pw");
		driver.findElement(By.id("vscLoginFormSubmit")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(10000);
		printScreen(driver,"account-summary");
		print(driver);
		takeScreenShot(driver);
		click(driver, "61014818"); 
		click(driver, "03234901");

		driver.close();
	}

	public static void click(WebDriver driver, String policy) throws Exception {
		driver.get("sample="+ policy);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(10000);
		printScreen(driver, policy);
	}

	public static void printScreen(WebDriver driver, String policy) {
		LocalDateTime currentTime = LocalDateTime.now();
		String formattedDateTime = currentTime.format(formatter);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("c:\\vsc\\" + policy + formattedDateTime + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(WebDriver driver){
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(3000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG", new File("c:\\vsc\\home.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	   public static void takeScreenShot(WebDriver driver) throws Exception {
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String fileName = UUID.randomUUID().toString();
	        File targetFile = new File("c:\\vsc\\" + fileName + ".png");
	        FileUtils.copyFile(scrFile, targetFile);
	    }
}