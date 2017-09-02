package com.vehicle.steps;

import com.assertions.AssertUsingExcel;
import com.assertions.AssertVehicleViewPage;
import com.lib.constants.BrowserDriver;
import com.lib.constants.TestContext;
import com.pages.HomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.logging.Logger;

/**
 * Created by anitha on 04/09/2017.
 */
public class VehicleSteps extends BaseSteps{
    public static final Logger LOGGER = Logger.getLogger(VehicleSteps.class.getName());

    @Given("^a user is on gov home page$")
    public void govHomePage() throws Throwable {
        BrowserDriver.loadPage(TestContext.getBaseUrl());
//        LOGGER.info("Window dimensions: " + getDriver().manage().window().getSize().toString());

    }

    @Given("^user is on vehicle input page$")
    public void vehicleInputPage() throws Throwable {
        homePage = new HomePage();
        homePage.start();
    }

    @Given("^user has valid registration number as \"([^\"]*)\"$")
    public void validRegistrationNumber(String registrationNumber) throws Throwable {
        homePage = new HomePage();
        homePage.enterRegistrationNumber(registrationNumber);
    }


    @When("^user selects continue$")
    public void selectsContinue() throws Throwable {
        homePage = new HomePage();
        homePage.go();
    }

    @Then("^user should be navigated to confirm vehicle page with registration number as \"(.*?)\"$")
    public void vehicleDetailsPage(String registrationNumber) throws Throwable {
        assertVehicleViewPage = new AssertVehicleViewPage();
        assertVehicleViewPage.assertConfirmVehiclePage(registrationNumber);
    }

    @Then("^user should be able to verify the vehicle colour to be \"(.*?)\"$")
    public void vehicleColour(String colourToAssert) throws Throwable {
        assertVehicleViewPage.assertVehicleColour(colourToAssert);
    }

    @Then("^user should be able to verify the vehicle model as \"(.*?)\"$")
    public void vehicleModel(String model) throws Throwable {
        assertVehicleViewPage.assertVehicleModel(model);
        homePage.confirm();
    }


    @Then("^user should be navigated to vehicle details page$")
    public void continueToVehicleDetailsPage() throws Throwable {
      homePage.confirmContinue();
    }


    @When("^user confirmed vehicle registration successfully$")
    public void userConfirmedVehicleRegistrationSuccessfully() throws Throwable {
        homePage.go();
        homePage.confirm();
        homePage.confirmContinue();
    }

    @Then("^vehicle had valid MOT$")
    public void vehicleHadValidMOT() throws Throwable {
        assertUsingExcel = new AssertUsingExcel();
        assertUsingExcel.assertVehicleMOT();
    }


}
