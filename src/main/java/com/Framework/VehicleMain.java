package com.Framework;

import com.assertions.AssertUsingExcel;
import com.assertions.AssertVehicleViewPage;
import com.lib.constants.BrowserDriver;
import com.lib.constants.TestContext;
import com.pages.HomePage;
import com.utils.Constants;
import com.utils.ExcelUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by anitha on 01/09/2017.
 */
public class VehicleMain {
    protected static HomePage homePage;
    protected static AssertVehicleViewPage assertVehicleViewPage;
    protected static AssertUsingExcel assertUsingExcel;

    private static WebDriver driver = null;

    public static void main(String[] args) throws Exception {

        ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Sheet1");
        BrowserDriver.loadPage(TestContext.getBaseUrl());
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        WebDriver augmentedDriver = new Augmenter().augment(driver);
//        File scrnFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrnFile,new File("C://Anitha//Personal//Vehicle//testData//screenShot//homePage.png"));

//        homePage();
        vehicleInputPage();
        vehicleNumber();
        selectsContinue();
//        vehicleDetailsPage();

        ExcelUtils.setCellData("Matched",1,6);
    }

    public static void homePage() throws Exception {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File scrnFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrnFile,new File("C://Anitha//Personal//Vehicle//testData//screenShot//homePage.png"));

    }
    public static void vehicleInputPage() throws IOException {
        homePage = new HomePage();
        homePage.start();
    }

    public static void vehicleNumber() throws Exception {
        String registrationNumber = ExcelUtils.getCellData(1,5);
        homePage = new HomePage();
        homePage.enterRegistrationNumber(registrationNumber);
//        File scrnFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrnFile,new File("C://Anitha//Personal//Vehicle//testData//screenShot//registrationNumberEntry.png"));

    }

    public static void selectsContinue() throws Exception {
        homePage = new HomePage();
        homePage.go();
        homePage.confirm();
//        File scrnFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrnFile,new File("C://Anitha//Personal//Vehicle//testData//screenShot//confirm.png"));
        homePage.confirmContinue();
    }

//    public static void vehicleDetailsPage() throws Exception {
//        assertUsingExcel = new AssertUsingExcel();
//
//        assertUsingExcel.assertVehicleMake();
////        File scrnFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
////        FileUtils.copyFile(scrnFile,new File("C://Anitha//Personal//Vehicle//testData//screenShot//viewVehicle.png"));
////
//         }
//
//
//




    }

