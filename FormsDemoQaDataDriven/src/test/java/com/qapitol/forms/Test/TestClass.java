package com.qapitol.forms.Test;

import com.qapitol.forms.Base.BaseClass;
import com.qapitol.forms.Utils.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

public class TestClass extends BaseClass {

    @DataProvider(name = "formData")
    public Object[][] formDataProvider() throws IOException {
        String FilePath ="src/test/resources/FileUpload/DataDriven.xlsx";
        return util.getExcelData(FilePath);
    }

    @Test(dataProvider = "formData")
    public void read(String FirstName, String LastName, String Email, String Gender, String Mobile,
                     String Dob, String Subjects, String Hobbies, String Picture, String Address,
                     String State, String City) throws IOException, InterruptedException, AWTException {

        scrollToElement(driver.findElement(By.xpath("//h5[text()='Student Registration Form']")));
        Thread.sleep(2000);

        driver.findElement(By.id("firstName")).sendKeys(FirstName);
        driver.findElement(By.id("lastName")).sendKeys(LastName);
        driver.findElement(By.id("userEmail")).sendKeys(Email);

        //Handle gender with switch case
        switch (Gender){
            case "Male":
                driver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();
                break;
            case "Female":
                driver.findElement(By.xpath("//label[@for='gender-radio-2']")).click();
                break;
            case "Other":
                driver.findElement(By.xpath("//label[@for='gender-radio-3']")).click();
                break;
            default:
                System.out.println("Entered invalid Gender");
                break;
        }

        scrollToElement(driver.findElement(By.id("userNumber-label")));

        Thread.sleep(2000);

        driver.findElement(By.id("userNumber")).sendKeys(Mobile);
        WebElement dob = driver.findElement(By.id("dateOfBirthInput"));
        dob.clear();
        dob.sendKeys(Dob);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        driver.findElement(By.id("subjectsInput")).sendKeys(Subjects);
        takeScreenshot(1);
        //Handle hobbies with switch case
        switch (Hobbies){
            case "Sports":
                driver.findElement(By.id("hobbies-checkbox-1")).click();
                break;
            case "Reading":
                driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']")).click();
                break;
            case "Music":
                driver.findElement(By.id("hobbies-checkbox-3")).click();
                break;
            default:
                System.out.println("Entered incorrect hobby");
                break;
        }
        scrollToElement(driver.findElement(By.id("subjects-label")));
        Thread.sleep(2000);

        takeScreenshot(2);

        driver.findElement(By.id("uploadPicture")).sendKeys(System.getProperty("user.dir")+Picture);
        driver.findElement(By.id("currentAddress")).sendKeys(Address);
        driver.findElement(By.id("react-select-3-input")).sendKeys(State);
        driver.findElement(By.id("react-select-3-option-1")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("react-select-4-input")).sendKeys(City);

        // Submit the form
        driver.findElement(By.id("submit")).click();
    }
}
