package org.ccTest.test;

import org.ccTest.test.stepdef.WelcomePage;
import org.ccTest.common.pageobject.KnownPage;

public enum KnownPages implements KnownPage {

    THEMEN ("Cucumber.io", WelcomePage.class);

	private final String pageName;
	private final Class<? extends PortalPage> pageClass;

	private KnownPages(final String pageName, final Class<? extends PortalPage> pageClass) {
		this.pageName = pageName;
		this.pageClass = pageClass;
	}

	@Override
	public String pageName() {
		return this.pageName;
	}

	@Override
	public Class<? extends PortalPage> pageClass() {
		return this.pageClass;
	}

}
