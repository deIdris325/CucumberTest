package org.ccTest.common.pageobject;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@Component
public class PageFactory {

    private KnownPagesCatalog knownPagesCatalog;

// @Autowired
    public PageFactory(KnownPagesCatalog knownPagesCatalog) {
        this.knownPagesCatalog = knownPagesCatalog;
    }

    public Page createPage(final String pageName, final WebDriver driver) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        for (KnownPage knownPage : knownPagesCatalog.getAllPages()) {
            if (knownPage.pageName().equals(pageName)) {
                return createPage(knownPage.pageClass(), driver);
            }
        }
        throw new IllegalArgumentException("Unknown page: " + pageName);
    }

    public <T extends Page> T createPage(final Class<T> pageClass, final WebDriver driver) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<T> pageConstructor = pageClass.getDeclaredConstructor(WebDriver.class);
        return pageConstructor.newInstance(driver);
    }

}
