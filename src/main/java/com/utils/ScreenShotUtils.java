package com.utils;

import com.lib.constants.BrowserDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by a.arulelango on 02/09/2017.
 */
public class ScreenShotUtils {

    private static WebDriver driver = null;


    public void takeScreenshot(String name) throws IOException {

        driver = BrowserDriver.getDriver();
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File scrnFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrnFile, new File("C://Anitha//Personal//Vehicle//testData//screenShot//"+name+".png"));

    }
}
