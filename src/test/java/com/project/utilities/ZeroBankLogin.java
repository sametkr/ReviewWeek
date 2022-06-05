package com.project.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ZeroBankLogin {

    public static void zeroBankLogin(String username, String password){
        WebDriver driver = Driver.getDriver();

        WebElement signInButton = driver.findElement(By.xpath("//button[@id='signin_button']"));
        signInButton.click();
        WebElement userNameInputBox = driver.findElement(By.xpath("//input[@id='user_login']"));
        userNameInputBox.sendKeys(username);
        WebElement passwordInputBox = driver.findElement(By.xpath("//input[@id='user_password']"));
        passwordInputBox.sendKeys(password);
        WebElement signInButton2 = driver.findElement(By.xpath("//input[@type='submit']"));
        signInButton2.click();
    }
}
