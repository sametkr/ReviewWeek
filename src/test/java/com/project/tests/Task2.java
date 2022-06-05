package com.project.tests;

import com.project.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Task2 {


    WebDriver driver = Driver.getDriver();

    @AfterMethod
    public void tearDownMethod(){
        driver.close();
    }

    @Test
    public void moneyGamingTest() {
        driver.navigate().to("https://moneygaming.qa.gameaccount.com/");

        WebElement joinNowButton = driver.findElement(By.xpath("//a[@class='newUser green']"));
        joinNowButton.click();

        Select select = new Select(driver.findElement(By.xpath("//select[@name='map(title)']")));
        select.selectByVisibleText("Mr");

        WebElement firstNameBox = driver.findElement(By.xpath("//input[@name='map(firstName)']"));
        firstNameBox.sendKeys("Adam");
        WebElement lastNameBox = driver.findElement(By.xpath("//input[@name='map(lastName)']"));
        lastNameBox.sendKeys("Sandler");

        WebElement termsAndConditionTickBox = driver.findElement(By.xpath("//input[@name='map(terms)']"));
        termsAndConditionTickBox.click();

        WebElement submitJoinNowButton = driver.findElement(By.xpath("//input[@class='promoReg green']"));
        submitJoinNowButton.click();

        WebElement validationMessage = driver.findElement(By.xpath("//label[@for='dob']"));
        String actualValidationMessage = validationMessage.getText();
        String expectedValidationMessage = "This field is required";

        Assert.assertEquals(expectedValidationMessage, actualValidationMessage);


    }
}
/*
Task 2:
1. Navigate to: https://moneygaming.qa.gameaccount.com/
2. Click the JOIN NOW button to open the registration page
3. Select a title value from the dropdown
4. Enter your first name and surname in the form
5. Check the tick box with text 'I accept the Terms and Conditions and certify that I am over
the age of 18.'
6. Submit the form by clicking the JOIN NOW button
7. Validate that a validation message with text ‘ This field is required’ appears under the date of
birth box

 */