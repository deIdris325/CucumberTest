package org.ccTest.test.stepdef;

import org.openqa.selenium.WebDriver;
import org.ccTest.test.PortalPage;

public class LogoutPage extends PortalPage {

    public LogoutPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean verifyIdentity(final WebDriver driver) {
        return driver.getTitle().equals("Login")
                && driver.getPageSource().contains("Abmeldung erfolgreich");
    }

}
