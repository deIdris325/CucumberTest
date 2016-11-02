package org.ccTest.test.stepdef;

import cucumber.api.java.en.Then;
import org.ccTest.common.stepdef.BasicStepdefs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ScrollStepdefs extends BasicStepdefs {

    @Then("^scroll to id \"([^\"]*)\"$")
    public void scrollTo_id(String idname) throws Throwable {
        WebElement element = driver.findElement(By.id(idname));
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

}
