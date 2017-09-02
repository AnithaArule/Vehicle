package com.lib.constants;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseWebElement extends HtmlElement {

    public static final Logger LOGGER = Logger.getLogger(BaseWebElement.class.getName());

    private final static int EXPLICIT_TIMEOUT = 60000;

    protected WebDriver getDriver() {
        return com.lib.constants.BrowserDriver.getDriver();
    }

    public String getTrimText() {
        return this.getWrappedElement().getText().trim();
    }

    public void click() {
        this.getWrappedElement().click();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    public String getPriceText() {
        return this.getTrimText().replaceAll("[^0-9.]", "");
    }

    /**
     * @return true if element id displayed and has height
     */
    public boolean isPresent() {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        try {
            return (this.getWrappedElement().isDisplayed() & this.getWrappedElement().getSize().getHeight() > 0);
        } catch (NoSuchElementException e) {
            return false;
        } catch (StaleElementReferenceException e2) {
            return false;
        } finally {
            getDriver().manage().timeouts().implicitlyWait(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS);
        }
    }


    /**
     * Click on element using javascript
     */
    public void clickJS() {
        String code = "var element = arguments[0];" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mousedown\", true, true);" + "element.dispatchEvent(clickEvent);"
                + "clickEvent = document.createEvent(\"MouseEvents\");" + "clickEvent.initEvent(\"click\", true, true);"
                + "element.dispatchEvent(clickEvent);" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mouseup\", true, true);" + "element.dispatchEvent(clickEvent);";
        ((JavascriptExecutor) getDriver()).executeScript(code, this.getWrappedElement());
    }

    public void type(String text) {
        this.getWrappedElement().clear();
        this.getWrappedElement().sendKeys(text);
    }

    public void blur() {
        executeCommand("blur()");
    }

    /**
     * Move mouse cursor to the middle of the element and click
     */
    public void clickAction() {
        new Actions(getDriver()).moveToElement(this.getWrappedElement()).click().build().perform();
    }

    /**
     * Wait for specified 'ExpectedCondition'
     *
     * @param condition
     */
    public void waitForExpectedCondition(final ExpectedCondition<?> condition, String message) {
        Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Timed out after " + (EXPLICIT_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' element " + message);
        wait.until(condition);
    }

    public void waitForElementPresent() {
        final BaseWebElement element = this;
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .pollingEvery(500, TimeUnit.MILLISECONDS).withMessage("Timed out after " + (EXPLICIT_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' element to appear");
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return element.isPresent();
            }
        };
        wait.until(condition);
    }

    public void waitForElementNotPresent() {
        final BaseWebElement element = this;
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .pollingEvery(500, TimeUnit.MILLISECONDS).withMessage("Timed out after " + (EXPLICIT_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' element to display");
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !element.isPresent();
            }
        };
        wait.until(condition);
    }

    public void waitForElementToDisappear() {
        waitForExpectedCondition(ExpectedConditions.attributeContains(this.getWrappedElement(), "style", "display: none"), "to disappear");
    }

    public void waitForAttributeNotContains(final String attribute, final String value) {
        final WebElement element = this.getWrappedElement();
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(EXPLICIT_TIMEOUT, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .pollingEvery(500, TimeUnit.MILLISECONDS).withMessage("Timed out after " + (EXPLICIT_TIMEOUT / 1000) + " seconds waiting for '" + this.getName()
                        + "' element '" + attribute + "' attribute not contains: '" + value + "'");
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !element.getAttribute(attribute).contains(value);
            }
        };
        wait.until(condition);
    }

    public void scrollTo() {
        this.executeCommand("scrollIntoView(false)");
    }

    public void setDisplayNone() {
        this.executeCommand("setAttribute('style', 'display:none')");
    }

    /**
     * Allows to execute Javascript on current element
     *
     * @param command without closing semicolon (e.g. to get innerHTML of an element specify command = "innerHTML")
     * @return result of command execution
     */
    private String executeCommand(String command) {
        return String.valueOf(((JavascriptExecutor) getDriver()).executeScript("return arguments[0]." + command + ";", this));
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}
