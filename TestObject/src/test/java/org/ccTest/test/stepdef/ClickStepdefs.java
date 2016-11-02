package org.ccTest.test.stepdef;

import cucumber.api.java.en.Then;
import org.ccTest.common.stepdef.BasicStepdefs;

import java.util.ArrayList;

public class ClickStepdefs extends BasicStepdefs {


    @Then("^the user clicks at \"([^\"]*)\"$")
    public void der_klickt_auf(String arg1) throws Throwable {
    	Thread.sleep(1500);
    	clickOn(arg1);
    }

    @Then("^navigate back$")
    public void navBack() throws Throwable {
        driver.navigate().back();
    }

    @Then("^change to tab$")
    public void der_Benutzer_wechselt_tab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
    }

}
