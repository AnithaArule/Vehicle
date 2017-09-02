package com.lib.constants;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by anitha on 02/09/2017.
 */
public class BrowserFactory {
    private static final Logger LOGGER = Logger.getLogger(BrowserFactory.class.getName());

    // browser to launch
    private static final String BROWSER_PROP_KEY = "browser";
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BrowserFactory.driver = driver;
    }

    /**
     * creates the browser driver specified in the system property "browser"
     * if no property is set then a firefox browser driver is created.
     * The allow properties are firefox, safari and chrome
     * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
     *
     * @return WebDriver
     */
    public static WebDriver createWebDriver() {
        String resourcePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        switch (Browsers.obtainBrowser(System.getProperty(BROWSER_PROP_KEY))) {
            case CHROME:
                if (System.getProperty("os.name").contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", resourcePath + "chromedriver.exe");
                }
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                setDriver(new ChromeDriver(options));
                LOGGER.info("New Chrome driver created");
                break;
           case FIREFOX:
            default:
                setDriver(createFFDriver());
                addAllBrowserSetup();
                LOGGER.info("New Firefox driver created");
                break;
        }
        return getDriver();
    }
    private static WebDriver createFFDriver() {
        FirefoxProfile profile = new FirefoxProfile();
        return new FirefoxDriver(profile);
    }

      private static void addAllBrowserSetup() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension dim = new org.openqa.selenium.Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        getDriver().manage().window().setSize(dim);
    }
}
