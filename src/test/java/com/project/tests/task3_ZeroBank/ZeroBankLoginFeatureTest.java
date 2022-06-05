package com.project.tests.task3_ZeroBank;

import com.project.utilities.ConfigurationReader;
import com.project.utilities.Driver;
import com.project.utilities.HandleWait;
import com.project.utilities.ZeroBankLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ZeroBankLoginFeatureTest {

    WebDriver driver = Driver.getDriver();

    @BeforeMethod
    public void navigationMethod() {
        driver.navigate().to("http://zero.webappsecurity.com/");
    }

    @Test(priority = 1)
    public void zeroBankLoginNegativeTestWithBlank() {
        ZeroBankLogin.zeroBankLogin("", "");

        String expectedErrorMessage = "Login and/or password are wrong.";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-error']")).getText();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 2)
    public void zeroBankLoginNegativeTestWithInvalidCredential() {
        ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username").toUpperCase(), ConfigurationReader.getProperty("password").toUpperCase());

        String expectedErrorMessage = "Login and/or password are wrong.";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-error']")).getText();

        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 3)
    public void zeroBankLoginValidCredentialVerifyAccountSummaryPage() {
        ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));

        String expectedTitle = "Zero - Account Summary";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "User failed to land on Account Summary Page after log-in");
    }
}
/*
Application under test : http://zero.webappsecurity.com/
Login credentials:
Username = “username”
Password = “password”
Login Feature
Only authorized users should be able to login to the application. When user logs in
with valid credentials, Account summary page should be displayed.
Users with wrong username or wrong password should not be able to login. Users
with blank username or password should also not be able to login. When user tries
to login with invalid information, error message “Login and/or password are wrong.”
should be displayed.
 */