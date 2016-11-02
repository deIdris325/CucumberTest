package org.ccTest.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationModalPage extends ModalPage {

    @FindBy(name = "yes")
    private WebElement okayButton;

    @FindBy(name = "no")
    private WebElement cancelButton;

    public ConfirmationModalPage(WebDriver driver) {
        super(driver);
    }

    public void confirm() {
        clickAndWaitFor(okayButton, By.className("feedbackPanel"));
    }

    public void cancel() {
        cancelButton.click();
    }

}
