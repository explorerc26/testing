package com.samples.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleJavascriptExecutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C://Users//srinivaas//Downloads//chromedriver_win32//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://google.com");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeAsyncScript("alert('hello world sync')");
		js.executeScript("alert('hello world')");
//		driver.close();
	}
	

}
