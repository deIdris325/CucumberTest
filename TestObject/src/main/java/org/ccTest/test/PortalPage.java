package org.ccTest.test;

import org.openqa.selenium.WebDriver;

import org.ccTest.common.pageobject.Page;


public abstract class PortalPage extends Page {
    private PageMenu pageMenu;

    public PortalPage(final WebDriver driver) {
        super(driver);
    }

    protected abstract boolean verifyIdentity(final WebDriver driver);

    public PageMenu getMenu() {
        if (pageMenu == null) {
            pageMenu = new PageMenu(driver);
        }
        return pageMenu;
    }

}
