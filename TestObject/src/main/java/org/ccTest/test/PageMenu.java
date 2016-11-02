package org.ccTest.test;

import org.ccTest.test.stepdef.LoginPage;
import org.ccTest.test.stepdef.LogoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageMenu extends PortalPage {

    @FindBy(linkText = "logout")
    private WebElement logoutLink;


    public PageMenu(final WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean verifyIdentity(final WebDriver driver) throws IllegalStateException {
        return driver.getTitle().equals("Logout");
    }

    public LoginPage navigateToLoginPage() {
        WebElement loginPageLink = driver.findElement(By.linkText("logout"));
        loginPageLink.click();

        return new LoginPage(driver);
    }

    public LogoutPage logout() {
        clickAndWaitFor(logoutLink, By.linkText("logout"));

        return new LogoutPage(driver);
    }


}
