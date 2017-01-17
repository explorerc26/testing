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

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class PrintScreen {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C://selenium//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\op\\screenshot.png"));
		printScreen(driver, "sample");
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
