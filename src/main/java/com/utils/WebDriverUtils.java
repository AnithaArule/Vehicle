package com.utils;


import com.lib.constants.AutomationConstants;
import com.lib.constants.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
    public static WebDriver driver = BrowserFactory.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 30);
    static Logger LOGGER = Logger.getLogger(WebDriverUtils.class);

     public static boolean isElementPresent(By element) {
        try {
            return driver.findElement(element).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public static void waitElementPresent(By element) {
        int counter = 0;

        while (counter <= AutomationConstants.DEFAULT_WAIT_SECONDS) {
            try {
//                wait.until(ExpectedConditions.presenceOfElementLocated(element));
                if (driver.findElement(element).isDisplayed())
                    return;
                else {
                    counter++;
                    System.out.println("Waiting for a sec...");
                    sleep(1);
                }
            } catch (Exception e) {
                //
            }
        }




}

    public static void waitAndClickOnLink(String link)
    {
        waitElementPresent(By.linkText(link));
        driver.findElement(By.linkText(link)).click();
    }

     public static String getVisibleText() {
        driver = BrowserFactory.getDriver();
        return driver.findElement(By.tagName("body")).getText();
    }


    public static void sleep(int i) {
        try {
            Thread.sleep(i * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
