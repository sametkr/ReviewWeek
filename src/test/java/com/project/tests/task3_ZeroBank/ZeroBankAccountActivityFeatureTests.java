package com.project.tests.task3_ZeroBank;

import com.project.utilities.ConfigurationReader;
import com.project.utilities.Driver;
import com.project.utilities.ZeroBankLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ZeroBankAccountActivityFeatureTests {

    WebDriver driver = Driver.getDriver();

    @BeforeMethod
    public void navigationMethod() {
        driver.navigate().to("http://zero.webappsecurity.com/");
    }


    @Test(priority = 1)
    public void accountActivityTitleVerification() {
        ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        driver.navigate().back();
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();

        String expectedTitle = "Zero - Account Activity";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title are not match");
    }

    //In the Account dropdown default option should be Savings.
    @Test(priority = 2)
    public void accountActivityDropDownDefaultOptionVerification() {
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();

        if (driver.getCurrentUrl().equals("http://zero.webappsecurity.com/login.html")) {
            driver.navigate().to("http://zero.webappsecurity.com/");
            ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            driver.navigate().back();

            driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
            driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();
        }

        Select select = new Select(driver.findElement(By.xpath("//select[@id='aa_accountId']")));
        String actualFirstSelectedOption = select.getFirstSelectedOption().getText();
        String expectedFirstSelectedOption = "Savings";
        Assert.assertEquals(actualFirstSelectedOption, expectedFirstSelectedOption, "First selected Option is not match");
    }

    //Account dropdown should have the following options: Savings, Checking, Loan, Credit Card, Brokerage.
    @Test(priority = 3)
    public void accountActivityDropDownOptionsVerification() {
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();

        if (driver.getCurrentUrl().equals("http://zero.webappsecurity.com/login.html")) {
            driver.navigate().to("http://zero.webappsecurity.com/");
            ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            driver.navigate().back();

            driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
            driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();
        }

        List<WebElement> list = driver.findElements(By.xpath("//select[@id='aa_accountId']/option"));
        List<String> list2 = Arrays.asList("Savings", "Checking", "Loan", "Credit Card", "Brokerage");
        for (int i = 0; i < list2.size(); i++) {
            Assert.assertEquals(list2.get(i), list.get(i).getText(), "Dropdown list are not match");
        }
    }

    //Transactions table should have column names Date, Description, Deposit, Withdrawal.
    @Test(priority = 4)
    public void transactionsTableVerification(){
        driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
        driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();

        if (driver.getCurrentUrl().equals("http://zero.webappsecurity.com/login.html")) {
            driver.navigate().to("http://zero.webappsecurity.com/");
            ZeroBankLogin.zeroBankLogin(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            driver.navigate().back();

            driver.findElement(By.xpath("//li[@id='onlineBankingMenu']")).click();
            driver.findElement(By.xpath("//span[@id='account_activity_link']")).click();
        }

        List<WebElement> list = driver.findElements(By.xpath("//div[@id=\"all_transactions_for_account\"]/table/thead/tr/th"));
        List<String> list2 = Arrays.asList("Date", "Description", "Deposit", "Withdrawal");

        for (int i = 0; i < list2.size(); i++) {
            Assert.assertEquals(list2.get(i), list.get(i).getText(), "Transactions Table headers are not match");
        }
    }

}
/*
Account Activity Feature
Account Activity page should have the title Zero – Account activity.
In the Account drop down default option should be Savings. Account drop down
should have the following options: Savings, Checking, Loan, Credit Card, Brokerage.
Transactions table should have column names Date, Description, Deposit,
Withdrawal.
NOTE: After you log in add extra step to refresh OR back method with Selenium to overcome security alert message.
 */