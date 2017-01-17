package com.samples.selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.jboss.netty.util.internal.SystemPropertyUtil;
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
		SimplePrintScreen printScreen = new SimplePrintScreen();
		printScreen.print();
	}
	
	public void print() throws Exception{
		// http://www.seleniumhq.org/download/ take java jars include in driver
		// https://sites.google.com/a/chromium.org/chromedriver/downloads
		System.setProperty("webdriver.chrome.driver","C://Users//srinivaas//Downloads//chromedriver_win32//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mdl.pvcorp.newyorklife.com/VSCWebApp/csrapp");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='USER']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys("");
		Thread.sleep(2000);
		printScreen(driver, "login");
		driver.findElement(By.xpath("//form[@name='Login']")).submit();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		Set<String> windowHandleSet = driver.getWindowHandles();
		String[] windowHandles = new String[windowHandleSet.size()];
		windowHandles = windowHandleSet.toArray(windowHandles);
		driver.switchTo().window(windowHandles[windowHandleSet.size()-1]);
		driver.findElement(By.xpath("//input[@name='clientidval']")).sendKeys("000008626");
		driver.findElement(By.xpath("//form[@id='SignOnForm']")).submit();
//		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);		
		Thread.sleep(5000);
		
		driver.switchTo().window(windowHandles[windowHandleSet.size()-2]);
		printScreen(driver, "accountSummary");
		String policy = "61014818";
		driver.get("https://mdl.pvcorp.newyorklife.com/VSCWebApp/servlet/com.nyl.vscii.policyserver.PolicyServerServlet?frompv=1&amp;contractNo="+ policy);
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		Thread.sleep(1000);
		printScreen(driver, "GPI-"+policy);
		Thread.sleep(1000);
		driver.close();
	}

	public static void click(WebDriver driver, String policy) throws Exception {
		driver.get("sample="+ policy);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(10000);
		printScreen(driver, policy);
	}

//	public static void printScreen(WebDriver driver, String policy) {
//		LocalDateTime currentTime = LocalDateTime.now();
//		String formattedDateTime = currentTime.format(formatter);
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(scrFile, new File("c:\\vsc\\" + policy + formattedDateTime + ".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void printScreen(WebDriver driver, String policy){
		LocalDateTime currentTime = LocalDateTime.now();
		String formattedDateTime = currentTime.format(formatter);
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(3000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG", new File("c:\\vsc\\" + policy +"-"+ formattedDateTime + ".png"));
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