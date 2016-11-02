package org.ccTest.test.pageobj;

import org.ccTest.common.pageobject.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class inputText extends Page {


    @FindBy(id = "item")
    private WebElement searchglobal_inTxt;

    @FindBy(xpath = "(//input[@id='item'])[2]")
    private WebElement search_inputText;




    public inputText(final WebDriver driver) {
        super(driver);
    }

    @Override
    protected boolean verifyIdentity(final WebDriver driver) {
        return driver.getTitle().contains("517 Suche | DW.COM")
                || driver.getTitle().contains("THEMEN | DW.COM")
                || driver.getTitle().contains("Media Center | DW.COM")
                || driver.getTitle().contains("TV | DW.COM")
                || driver.getTitle().contains("DEUTSCH LERNEN | DW.COM");
    }

    public void searchText(String txt) {
        search_inputText.clear();
        search_inputText.sendKeys(txt);
    }

    public void searchGlobalText(String txt) {
        searchglobal_inTxt.clear();
        searchglobal_inTxt.sendKeys(txt);
    }
}
