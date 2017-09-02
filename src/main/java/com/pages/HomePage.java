package com.pages;

import com.utils.ScreenShotUtils;
import com.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

/**
 * Created by anitha on 02/09/2017.
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"get-started\"]/a")
    private WebElement startNow;

    @FindBy(xpath = "//*[@id=\"Vrm\"]")
    private WebElement registrationNumberInputText;
    private ScreenShotUtils screenShotUtils = new ScreenShotUtils();

    public void start() throws IOException {
        WebDriverUtils webDriverUtils = new WebDriverUtils();

        WebElement startNow = getDriver().findElement(By.xpath("//*[@id=\"get-started\"]/a"));

        screenShotUtils.takeScreenshot("01_homePage");
        startNow.click();


    }

    public void enterRegistrationNumber(String registration) throws IOException {
        WebElement registrationField= getDriver().findElement(By.id("Vrm"));
        registrationField.clear();
        screenShotUtils.takeScreenshot("02_vehicleEnquiryPage");
        registrationField.sendKeys(registration);
        screenShotUtils.takeScreenshot("03_RegNumberEntered");

    }

    public void go(){
        WebDriverUtils.isElementPresent(By.name("Continue"));
        WebElement continueToVehicleDetailsPage = getDriver().findElement(By.name("Continue"));
        continueToVehicleDetailsPage.click();
    }



        public void confirm() throws IOException {
            screenShotUtils.takeScreenshot("04_ConfirmVehiclePage");
            WebElement selectYes = getDriver().findElement(By.xpath("//*[@id=\"Correct_True\"]"));
            selectYes.click();
            screenShotUtils.takeScreenshot("05_selectYesNo");

        }
        public void confirmContinue() throws IOException {
            WebElement continueToDetails = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/button"));
            continueToDetails.click();
            screenShotUtils.takeScreenshot("06_VehicleDetails");
        }


}
