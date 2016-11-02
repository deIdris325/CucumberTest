package org.ccTest.common.stepdef;

import org.ccTest.common.SharedDriver;
import org.ccTest.common.pageobject.PageFactory;
import org.jsoup.Jsoup;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


@ContextConfiguration("classpath:applicationContext.xml")
public class BasicStepdefs {

    @Autowired
    protected SharedDriver driver;
    protected WebDriverWait wait;

    @Autowired
    private PageFactory pageFactory;

    protected void clickOn(final String locator) {
        try {
            List<By> possibleBys = fetchPossibleBysFor(locator);
            driver.setImplicitWaitTo(1);

            for (By possibleBy : possibleBys) {
                try {
                    driver.findElement(possibleBy).click();
                    return;
                } catch (NoSuchElementException nsee) {
                    // ignore
                }
            }
            throw new NoSuchElementException("No Element found for locator '" + locator + "'!");
        } finally {
            driver.resetImplicitWaitToDefault();
        }
    }

    private List<By> fetchPossibleBysFor(final String locator) {
        List<By> bys = new ArrayList<By>();
        bys.add(By.linkText(locator));
        bys.add(By.partialLinkText(locator));
        bys.add(By.id(locator));
        bys.add(By.name(locator));
        bys.add(By.cssSelector(locator));
        bys.add(By.xpath(locator));
        bys.add(By.xpath("//input[@value='" + locator + "']"));
        bys.add(By.tagName(locator));
        bys.add(By.className(locator));
        bys.add(By.xpath("//input[@value='" + locator + "']"));
        bys.add(By.xpath("//button[@value='" + locator + "']"));
        bys.add(By.xpath("//img[@title='" + locator + "']"));
        bys.add(By.xpath("//button[contains(.,'" + locator + "')]"));
        return bys;
        //driver.findElement(By.xpath("//button[contains(.,'Live ändern')]"));
    }

    public void clickOption(String option) {
        driver.findElement(By.xpath("//select/option[normalize-space(text())='" + option + "']")).click();
    }

    public void clickOptionContains(String option) {
        driver.findElement(By.xpath("//select/option[contains(text(), '" + option + "')]")).click();
    }

    public void clickOptionWithValue(String option) {
        driver.findElement(By.xpath("//select/option[@value='" + option + "']")).click();
    }

    public String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public PageFactory getPageFactory() {
        return pageFactory;
    }


    public void verfiyPresentImage(String arg1) throws Throwable {
        WebElement ImageFile = driver.findElement(By.xpath("//img[@src='/image/" + arg1 + "']"));

        Boolean ImagePresent = (Boolean) driver.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
        if (!ImagePresent) {
            System.out.println("Image not displayed.");
        } else {
            System.out.println("Image displayed.");
        }
    }

    public void verifyImageSize(String inputimgWidth, String inputimgHeight, String imgPath) throws Throwable {
        // get the intrinsic size with the getAttribute method
        WebElement ele = driver.findElement(By.xpath("//div[@id='" + imgPath + "']/div/a/img"));
        String naturalWidth = ele.getAttribute("width");
        String naturalHeight = ele.getAttribute("height");
        System.out.print("Hoch: " + naturalHeight);
        System.out.print("\nBreit: " + naturalWidth);

        inputimgHeight.equals(naturalWidth);
        inputimgWidth.equals(naturalHeight);

        // assertEquals(inputimgWidth, naturalWidth);
        // Assert.assertEquals(inputimgHeight, naturalHeight);

    }

    protected void selectFromDropdown(final String locator, String text) {
        try {
            List<By> possibleBys = fetchPossibleBysFor(locator);
            driver.setImplicitWaitTo(2);

            for (By possibleBy : possibleBys) {
                try {
                    //new Select(driver.findElement(By.id("selection"))).selectByVisibleText(arg1);
                    new Select(driver.findElement(possibleBy)).selectByVisibleText(text);
                    return;
                } catch (NoSuchElementException nsee) {
                    // ignore
                }
            }
            throw new NoSuchElementException("No Element found for locator '" + locator + "'!");
        } finally {
            driver.resetImplicitWaitToDefault();
        }
    }

    public String currentDateDiffLang(String inputLanguage) throws Throwable {
        Date curDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd.MM.YYYY", new Locale(inputLanguage));
        String strTodaysDate = dateFormat.format(curDate);
        return strTodaysDate;
        //WebElement siteDate = driver.findElement(By.xpath("//th[contains(.,'"+strTodaysDate+"')]"));

    }

    public String currentUSDateDiffLang(String inputLanguage) throws Throwable {
        Date curDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd", new Locale(inputLanguage));
        String strTodaysDate = dateFormat.format(curDate);
        return strTodaysDate;
        //WebElement siteDate = driver.findElement(By.xpath("//th[contains(.,'"+strTodaysDate+"')]"));

    }

    public static Date subtractDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public String rgb2hex(String color) {
        String s = color.substring(5);
        StringTokenizer st = new StringTokenizer(s);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        Color c = new Color(r, g, b);
        String hex = "#" + Integer.toHexString(c.getRGB()).substring(2);

        return hex;
    }

    public void selectOptionWithText(String textToSelect) {
        try {
            WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
            wait.until(ExpectedConditions.visibilityOf(autoOptions));

            List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
            for (WebElement option : optionsToSelect) {
                if (option.getText().equals(textToSelect)) {
                    System.out.println("Trying to select: " + textToSelect);
                    option.click();
                    break;
                }
            }

        } catch (NoSuchElementException e) {
            System.out.println(e.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void TypeInField(String xpath, String value) throws Throwable {
        String val = value;
        WebElement element = driver.findElement(By.xpath(xpath));
        //boolean textPresent = driver.findElement(By.xpath("//span[contains(.,'Politik')]")).isEnabled();
        element.clear();

        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            // System.out.print("\n ### xpath contains Politik: " + textPresent);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
            sleepTime(1000);

        }
    }

    public void sleepTime(int time) throws Throwable {
        Thread.sleep(time);
    }

}
