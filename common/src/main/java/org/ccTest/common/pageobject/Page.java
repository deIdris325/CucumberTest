package org.ccTest.common.pageobject;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class Page {

    private static final int DEFAULT_TIMEOUT = 10;
    
    protected final WebDriver driver;

    public Page(final WebDriver driver) {
        if (verifyIdentity(driver)) {
            this.driver = driver;
            PageFactory.initElements(this.driver, this);
        } else {
            throw new IllegalStateException("This is not the requested page '" + this.getClass().getName() + "'! Current page title is '" + driver.getTitle() + "' at " + driver.getCurrentUrl());
        }
    }

    protected abstract boolean verifyIdentity(final WebDriver driver);

    protected void typeValueIfNotNull(final String value, final WebElement target) {
        if (value != null) {
            target.clear();
            target.sendKeys(value);
        }
    }

    protected void clickAndWaitFor(final WebElement clickableElement, final By... possibleElements) {
        try {
            clickableElement.click();
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            new FluentWait<WebElement>(clickableElement)
                    .withTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .pollingEvery(200, TimeUnit.MILLISECONDS)
                    .until(new Predicate<WebElement>() {
                        public boolean apply(WebElement element) {
                            return verifyPresenceOf(possibleElements);
                        }

                        private boolean verifyPresenceOf(final By... possibleElements) {
                            for (By possibleElement : possibleElements) {
                                try {
                                    return driver.findElement(possibleElement).isDisplayed();
                                } catch (NoSuchElementException nsee) {
                                    // ignore
                                } catch (StaleElementReferenceException sere) {
                                    // ignore
                                }
                            }
                            return false;
                        }
                    });
        } finally {
            driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        }
    }

    public void clickCheckboxIfNecessary(final String checkboxValue, final String targetId) {
        if (isYes(checkboxValue) || isNo(checkboxValue)) {
            WebElement checkbox = driver.findElement(By.id(targetId));
            boolean selected = checkbox.isSelected();

            if ((!selected && isYes(checkboxValue))
                    || (selected && isNo(checkboxValue))) {
                checkbox.click();
            }
        } else {
            throw new IllegalArgumentException("Illegal value [" + checkboxValue + "] for checkbox [" + targetId + "]! Valid options are: [ja, j, nein, n]");
        }
    }

    private boolean isYes(final String checkboxValue) {
        return ("yes".equalsIgnoreCase(checkboxValue) || "y".equalsIgnoreCase(checkboxValue));
    }

    private boolean isNo(final String checkboxValue) {
        return ("no".equalsIgnoreCase(checkboxValue) || "n".equalsIgnoreCase(checkboxValue));
    }

    protected void setTextForSelectbox(final WebElement targetSelectbox, final String text) {
        Select selectbox = new Select(targetSelectbox);
        selectbox.selectByVisibleText(text);
    }

    protected boolean verifyPictureInStyle(final WebElement targetElement, final String pictureName) {
        String style = targetElement.getAttribute("style");
        return style.contains(pictureName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public boolean elementExists(String id) {
        return driver.findElement(By.id(id)) != null;
    }

    public void clickOption(String option) {
        driver.findElement(By.xpath("//select/option[contains(text(), '" + option + "')]")).click();
    }
    
    public void clickLinkByHref(String linktext) {
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        Iterator<WebElement> i = anchors.iterator();
        boolean veryfied = false;
        WebElement anchor = null;
        while(i.hasNext()) {
            anchor = i.next();
            if(anchor.getAttribute("href").contains(linktext)) {
                veryfied = true;
                break;
            }
        }
        if(veryfied) {
            anchor.click();
        } else {
            throw new IllegalArgumentException("Current page doesn't contain a hyperlink with text <a href: [" + linktext + "]>!");
        }
    }

}
