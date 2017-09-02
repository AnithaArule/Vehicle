package com.vehicle.steps;

import com.assertions.AssertUsingExcel;
import com.pages.HomePage;
import com.utils.ExcelUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created by anitha on 02/09/2017.
 */
public class VehicleStepsUsingExcel extends BaseSteps{



    @And("^user selects continue to confirm$")
    public void selectsContinueToConfirm() throws Throwable {
        homePage = new HomePage();
        homePage.go();
        homePage.confirm();
        homePage.confirmContinue();
    }

    @And("^user enters valid registration number from excel with row (\\d+) and column (\\d+)$")
    public void vehicleInputPage(int row, int column) throws Throwable {
        String registrationNumber = ExcelUtils.getCellData(row,column);
        homePage = new HomePage();
        System.out.println(registrationNumber);
        homePage.enterRegistrationNumber(registrationNumber);
    }

    @Then("^user should be able to verify the vehicle model as in row (\\d+) column (\\d+)$")
    public void assertVehicleModel(int row, int column) throws Throwable {
        assertUsingExcel = new AssertUsingExcel();
        assertUsingExcel.assertVehicleMake(row,column);
        ExcelUtils.setCellData("Make Matched",1,6);
    }


    @Then("^assert the vehicle colour to be as in row (\\d+) coloumn (\\d+)$")
    public void assertVehicleColour(int row, int column) throws Throwable {
        assertUsingExcel = new AssertUsingExcel();
        assertUsingExcel.assertVehicleColour(row,column);
        ExcelUtils.setCellData("Colour Matched",1,7);
    }
}
