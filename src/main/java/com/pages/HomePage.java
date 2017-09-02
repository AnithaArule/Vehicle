package com.pages;

import com.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by anitha on 02/09/2017.
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"get-started\"]/a")
    private WebElement startNow;

    @FindBy(xpath = "//*[@id=\"Vrm\"]")
    private WebElement registrationNumberInputText;

    public void start() {
        WebDriverUtils webDriverUtils = new WebDriverUtils();

        WebElement startNow = getDriver().findElement(By.xpath("//*[@id=\"get-started\"]/a"));
        startNow.click();


    }

    public void enterRegistrationNumber(String registration){

        WebElement registrationField= getDriver().findElement(By.id("Vrm"));
        registrationField.clear();
        registrationField.sendKeys(registration);
    }

    public void go(){
        WebDriverUtils.isElementPresent(By.name("Continue"));
        WebElement continueToVehicleDetailsPage = getDriver().findElement(By.name("Continue"));
        continueToVehicleDetailsPage.click();
    }



        public void confirm(){
            WebElement selectYes = getDriver().findElement(By.xpath("//*[@id=\"Correct_True\"]"));
            selectYes.click();

        }
        public void confirmContinue(){
            WebElement continueToDetails = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/button"));
            continueToDetails.click();
        }


}
