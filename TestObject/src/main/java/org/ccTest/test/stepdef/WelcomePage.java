package org.ccTest.test.stepdef;

import org.openqa.selenium.WebDriver;

import org.ccTest.test.PortalPage;

public class WelcomePage extends PortalPage {

	public WelcomePage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean verifyIdentity(final WebDriver driver) {
		return driver.getTitle().equals("THEMEN | DW.COM")
				&& driver.getPageSource().contains("THEMEN | DW.COM");
	}

}
