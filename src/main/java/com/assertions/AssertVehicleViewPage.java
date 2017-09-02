package com.assertions;

import com.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;


/**
 * Created by anitha on 31/08/2017.
 */
public class AssertVehicleViewPage extends BasePage{

public void assertConfirmVehiclePage(String registrationNumber){
    WebElement confirmVehicle = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[1]/span[1]"));
    WebElement registrationNumberOnThePage = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[1]/span[2]"));
    String numberToAssert = registrationNumberOnThePage.getText();
    assertEquals(registrationNumber,numberToAssert);

}

    public void assertVehicleColour(String colourToAssert){
        WebElement colourInVehicleDetailsPage = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong"));
        String colour = colourInVehicleDetailsPage.getText();
        assertEquals(colourToAssert,colour);
    }

    public void assertVehicleModel(String modelToAssert){
        WebElement modelInVehicleDetailsPage = getDriver().findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong"));
        String model = modelInVehicleDetailsPage.getText();
        assertEquals(modelToAssert,model);
    }
}
