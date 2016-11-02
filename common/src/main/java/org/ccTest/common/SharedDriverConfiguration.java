package org.ccTest.common;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

@Configuration
public class SharedDriverConfiguration {

    @Bean
    public SharedDriver getSharedDriver() throws MalformedURLException {
        WebDriver browser =Firefox();
        // SharedDriver sharedDriver = new SharedDriver(browser);
        return new SharedDriver(browser);
    }

    private static WebDriver Chrome() {
        System.out.println("Called openBrowser Chrome");

        String Remote_ChromeDriverPath = "../common/src/main/java/org/ccTest/common/chromedriver/chromedriver224.exe";
        System.setProperty("webdriver.chrome.driver", Remote_ChromeDriverPath);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        capabilities.getBrowserName();
        capabilities.getVersion();
        return new ChromeDriver(capabilities);
    }

    private static WebDriver Firefox() {
        System.out.println("Called openBrowser Firefox");
        return new FirefoxDriver();
    }

    private static WebDriver InternetExplorer() {
        System.out.println("Called openBrowser Internet Explorer");

        String Remote_IEDriverPath = "../common/src/main/java/org/ccTest/common/chromedriver/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", Remote_IEDriverPath);
        DesiredCapabilities capab;
        capab = DesiredCapabilities.internetExplorer();
        capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        return new InternetExplorerDriver(capab);
    }

    public WebDriver loadDriver(WebDriver driver, String browser){

        DesiredCapabilities capab;

        //Return driver for each browser version
        switch(browser){
            case "IE":
                System.setProperty("webdriver.ie.driver", "../common/src/main/java/org/ccTest/common/chromedriver/IEDriverServer.exe");
                capab = DesiredCapabilities.internetExplorer();
                capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(capab);
                break;
            case "FF":
                driver = new FirefoxDriver();
                break;
            case "CH":
                System.setProperty("webdriver.chrome.driver", "../common/src/main/java/org/ccTest/common/chromedriver/chromedriver224.exe");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        return driver;
    }


}
