package org.ccTest.test.stepdef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.ccTest.test.PortalPage;

public class LoginPage extends PortalPage {

	@FindBy(id = "uname")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(css = "input[type='submit']")
	private WebElement loginButton;


	public LoginPage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean verifyIdentity(final WebDriver driver) {
		return driver.getTitle().equals("Login")
				&& driver.getPageSource().contains("Login");
	}

	public HomePage login(String username, String password) {
		typeValueIfNotNull(username, usernameInput);
		typeValueIfNotNull(password, passwordInput);
		loginButton.click();

		return new HomePage(driver);
	}

}