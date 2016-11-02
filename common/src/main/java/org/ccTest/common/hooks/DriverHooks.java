package org.ccTest.common.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.ccTest.common.SharedDriver;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;

public class DriverHooks {
//https://www.tutorialspoint.com/cucumber/cucumber_hooks.htm
    private final SharedDriver driver;

    @Autowired
    public DriverHooks(SharedDriver driver) {
        this.driver = driver;
    }

    @Before(order = 1)
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    @After(order = 3)
    public void embedScreenshotIfFailed2(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

}
