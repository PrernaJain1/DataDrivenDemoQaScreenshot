package com.qapitol.forms.Base;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;

    @BeforeTest
    public void preSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement form= driver.findElement(By.xpath("//h5[text()='Forms']"));
        scrollToElement(form);
        form.click();
        driver.findElement(By.xpath("//span[text()='Practice Form']")).click();
        scrollToElement(driver.findElement(By.xpath("//h1[text()='Practice Form']")));
    }

    @AfterTest
    public void postSetDown(){
        driver.quit();
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public static void takeScreenshot(int i) throws IOException {
        //cast driver to TakeScreenshot
        TakesScreenshot screenshot= (TakesScreenshot) driver;

        //Capture screenshot and store into a file
        File srcFile= screenshot.getScreenshotAs(OutputType.FILE);

        //Define destination path for screenshot
        File destFile = new File("src/test/resources/Screenshot/abc"+i+".png") ;

        //Copy file to destination
        FileUtils.copyFile(srcFile,destFile);

        System.out.println("Screenshot captured successfully");
    }

}
