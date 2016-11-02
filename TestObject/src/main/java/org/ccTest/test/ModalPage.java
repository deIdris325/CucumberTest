package org.ccTest.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalPage extends PortalPage {

    @FindBy(className = "close")
    private WebElement closeButton;

    public ModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean verifyIdentity(WebDriver driver) {
        return driver.findElement(By.className("close")).isDisplayed();
    }

    public void closeModalPage() {
        closeButton.click();
    }

}
