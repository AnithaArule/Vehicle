package com.assertions;

import com.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

/**
 * Created by anitha on 02/09/2017.
 */
public class AssertError extends BasePage{

//registration number
    //*[@id="content"]/form/div/div/div[1]/ul/a

//   asserting for valid error for invalid input
    public void assertInvalidInputError(String error)throws Exception{
        WebElement errorMessageElement = getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div[1]/p"));
        System.out.println((errorMessageElement.getText()));
//        assertNotNull(errorMessageElement.getText());
        assertEquals(error,errorMessageElement);
    }

}
