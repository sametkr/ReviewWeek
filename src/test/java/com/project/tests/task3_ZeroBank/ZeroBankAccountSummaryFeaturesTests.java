package com.project.tests.task3_ZeroBank;

import com.project.utilities.ConfigurationReader;
import com.project.utilities.Driver;
import com.project.utilities.ZeroBankLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ZeroBankAccountSummaryFeaturesTests {

    WebDriver driver = Driver.getDriver();

    @BeforeMethod
    public void navigationMethod() {
        driver.navigate().to("http://zero.webappsecurity.com/");
    }

    @Test(priority = 1)
    public void accountSummaryTitleVerification() {
        ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        driver.navigate().back();
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_summary_link']")).click();

        String expectedTitle = "Zero - Account Summary";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title are not match");
    }

    @Test(priority = 2)
    public void accountTypeHeadersVerification() {
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_summary_link']")).click();

        if (driver.getCurrentUrl().equals("http://zero.webappsecurity.com/login.html")) {
            driver.navigate().to("http://zero.webappsecurity.com/");
            ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            driver.navigate().back();

            driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
            driver.findElement(By.xpath("//span[@id='account_summary_link']")).click();
        }

        //summary page should have to following account types: Cash Accounts, Investment Accounts, Credit Accounts, Loan Accounts.
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='offset2 span8']/h2"));
        List<String> list2 = Arrays.asList("Cash Accounts", "Investment Accounts", "Credit Accounts", "Loan Accounts");
        for (int i = 0; i < list2.size(); i++) {
            Assert.assertEquals(list2.get(i), list.get(i).getText(), "Account type headers are not match");
        }

    }
    @Test(priority = 3)
    public void creditAccountTableColumnsVerification() {
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_summary_link']")).click();

        if (driver.getCurrentUrl().equals("http://zero.webappsecurity.com/login.html")) {
            driver.navigate().to("http://zero.webappsecurity.com/");
            ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            driver.navigate().back();

            driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
            driver.findElement(By.xpath("//span[@id='account_summary_link']")).click();
        }

        //Credit Accounts table must have columns Account, Credit Card and Balance.
        List<WebElement> list = driver.findElements(By.xpath("//div[3]/div/table/thead/tr/th"));
        List<String> list2 = Arrays.asList("Account", "Credit Card", "Balance");
        for (int i = 0; i < list2.size(); i++) {
            Assert.assertEquals(list2.get(i), list.get(i).getText(), "Account table columns text are not match");
        }

    }
}
/*
Account summary Feature
Account summary page should have the title Zero – Account summary. Account
summary page should have to following account types: Cash Accounts, Investment
Accounts, Credit Accounts, Loan Accounts. Credit Accounts table must have columns
Account, Credit Card and Balance.
 */





