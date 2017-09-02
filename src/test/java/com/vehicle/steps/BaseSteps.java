package com.vehicle.steps;

import com.assertions.AssertUsingExcel;
import com.assertions.AssertVehicleViewPage;
import com.pages.HomePage;
import com.lib.constants.BrowserDriver;
import org.openqa.selenium.WebDriver;

/**
 * Created by anitha on 04/09/2017.
 */
public class BaseSteps {
    protected HomePage homePage;
    protected AssertVehicleViewPage assertVehicleViewPage;
    protected AssertUsingExcel assertUsingExcel;

    protected WebDriver getDriver() {
        return BrowserDriver.getDriver();
    }

}
