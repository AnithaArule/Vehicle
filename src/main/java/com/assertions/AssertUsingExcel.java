package com.assertions;

import com.pages.BasePage;
import com.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by anitha on 01/09/2017.
 */
public class AssertUsingExcel extends BasePage {

// asserting for MOT element to be present with data, not null
    public void assertVehicleMOT()throws Exception{
        //TODO - create constants
        WebElement motElement = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div[1]/p"));
        System.out.println((motElement.getText()));
        assertNotNull(motElement.getText());
    }

// asserting vehicle make in the vehicle view page
    public void assertVehicleMake(int row, int coloumn) throws Exception {
        String make = ExcelUtils.getCellData(row, coloumn);
        WebElement vehicleViewPage = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[2]/h1"));
        WebElement vehicleModelElement = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[4]/ul/li[1]/span[2]/strong"));
        String modelFromPage = vehicleModelElement.getText();
        assertEquals(make,modelFromPage);

        }

    // asserting vehicle colour in the vehicel view page
    public void assertVehicleColour(int row, int column) throws Exception {

        String colour = ExcelUtils.getCellData(row, column);
        WebElement vehicleColourElement = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[4]/ul/li[9]/span[2]/strong"));
        String colourFromPage = vehicleColourElement.getText();
        assertEquals(colour,colourFromPage);
    }

//    asserting vehicle year of manufacture in vehicel view page
    public void assertVehicleYear()throws Exception{

        String year = ExcelUtils.getCellData(1, 3);
        WebElement vehicleYearElement = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[4]/ul/li[3]/span[2]/strong"));
        String yearFromPage = vehicleYearElement.getText();
        assertEquals(year,yearFromPage);
    }
//   asserting vehicle fule type in vehicle view page
    public void assertVehicleFuelType()throws Exception{

        String fuel = ExcelUtils.getCellData(1, 4);
        WebElement vehicleFuelTypeElement = getDriver().findElement(By.xpath("//*[@id=\"FuelTypeShown\"]"));
        String fuelFromPage = vehicleFuelTypeElement.getText();
        assertEquals(fuel,fuelFromPage);
    }
}
