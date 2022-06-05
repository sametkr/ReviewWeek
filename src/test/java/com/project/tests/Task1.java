package com.project.tests;

import com.project.utilities.ConfigurationReader;
import com.project.utilities.Driver;
import com.project.utilities.HandleWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1 {

    @Test
    public void amazonTest() {

        Driver.getDriver().navigate().to("https://www.amazon.com");

        //Searching Item
        WebElement searchBox = Driver.getDriver().findElement(By.xpath("//input[@aria-label='Search']"));
        searchBox.sendKeys(ConfigurationReader.getProperty("item") + Keys.ENTER);

        //Selecting first appear item
        WebElement firstItem = Driver.getDriver().findElement(By.xpath("//div[@data-component-type='s-impression-logger']/div/div/div/div/span/a/div/img[@data-image-index='1']"));
        firstItem.click();

        //getting first selecting item price
        String firstItemPriceText = Driver.getDriver().findElement(By.xpath("//div[@id='corePrice_desktop']//span[@aria-hidden='true']")).getText();
        double firstItemPrice = Double.parseDouble(firstItemPriceText.substring(1));

        //selecting quantity from dropdown
        WebElement quantityDropdown = Driver.getDriver().findElement(By.xpath("//div[@id='selectQuantity']//span[@class='a-button-text a-declarative']"));
        quantityDropdown.click();
        Driver.getDriver().findElement(By.xpath("//li[@aria-labelledby='quantity_1']")).click();

        //adding item to cart
        Driver.getDriver().findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

        //navigate to cart
        Driver.getDriver().findElement(By.xpath("//a[@id='nav-cart']")).click();


        //Asserting to quantity and total price
        String cartTotalPriceText = Driver.getDriver().findElement(By.xpath("//*[@id=\"sc-subtotal-amount-buybox\"]/span/span/span[2]/span[2]")).getText().trim() +
                '.' + Driver.getDriver().findElement(By.xpath("//*[@id=\"sc-subtotal-amount-buybox\"]/span/span/span[2]/span[3]")).getText().trim();
        double actualTotalPrice = Double.parseDouble(cartTotalPriceText);
        double expectedTotalPrice = firstItemPrice * 2;
        Assert.assertEquals(actualTotalPrice, expectedTotalPrice);

        int actualQuantity = Integer.parseInt(Driver.getDriver().findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText());
        int expectedQuantity = 2;
        Assert.assertEquals(actualQuantity, expectedQuantity);

        //Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3
        quantityDropdown = Driver.getDriver().findElement(By.xpath("//span[@class='a-button-text a-declarative']"));
        quantityDropdown.click();
        Driver.getDriver().findElement(By.xpath("//li[@aria-labelledby='quantity_1']")).click();

        //Assert that the total price and quantity has been correctly changed
        Assert.assertEquals(firstItemPrice, actualTotalPrice/2);
        Assert.assertEquals(1, actualQuantity-1);




    }

}
/*
Task 1: The task consists on the next steps:
        Go to https://www.amazon.com
        Search for "hats for men" (Call from Configuration.properties file)
        Add the first hat appearing to Cart with quantity 2
        Open cart and assert that the total price and quantity are correct
        Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3
        Assert that the total price and quantity has been correctly changed
The goal of this test is to check if you are able to automate a test of a given website,
but we'd like you to also demonstrate the coding quality, structure, and style of the deliverables.
 */

/*

 */