package org.ccTest.test.stepdef;

import cucumber.api.java.en.Given;
import org.ccTest.common.stepdef.BasicStepdefs;

import static org.junit.Assert.assertTrue;

public class VerifyTextStepdefs extends BasicStepdefs {



    @Given("^call the website \"([^\"]*)\"$")
    public void call_the_website(String url) throws Throwable {
        driver.get(url);

    }

    @Given("^verify as Text \"([^\"]*)\"$")
    public void verify_as_Text(String searchText) throws Throwable {
        String cleansedPageSource = html2text(driver.getPageSource());
        assertTrue(cleansedPageSource.contains(searchText));
    }

}
