package com.project.tests;

import com.project.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Task3 {

    WebDriver driver = Driver.getDriver();

    @Test
    public void webAppSecurity(){

        driver.navigate().to("http://zero.webappsecurity.com/");

    }
}
/*
Task 3
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
Account summary Feature
Account summary page should have the title Zero – Account summary. Account
summary page should have to following account types: Cash Accounts, Investment
Accounts, Credit Accounts, Loan Accounts. Credit Accounts table must have columns
Account, Credit Card and Balance.
Account Activity Feature
Account Activity page should have the title Zero – Account activity.
In the Account drop down default option should be Savings. Account drop down
should have the following options: Savings, Checking, Loan, Credit Card, Brokerage.
Transactions table should have column names Date, Description, Deposit,
Withdrawal.
NOTE: After you log in add extra step to refresh OR back method with Selenium to overcome security alert message.

 */