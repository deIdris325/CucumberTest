/**
 * Created by nawidi on 27.05.2016.
 */

package org.ccTest.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class SharedDriver extends EventFiringWebDriver {

    private static final long DEFAULT_IMPLICIT_WAIT_SECONDS = 2;
    private final Thread THREAD;

    public SharedDriver(final WebDriver DRIVER) throws MalformedURLException {
        super(DRIVER);
        
		THREAD = new Thread() {
			@Override
			public void run() {
				if (DRIVER.manage().window() != null) {
					DRIVER.quit();
                    DRIVER.switchTo().alert().accept();
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(THREAD);
        
        setImplicitWaitTo(DEFAULT_IMPLICIT_WAIT_SECONDS);
    }

    
    @Override
    public void close() {
        if (Thread.currentThread() != THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver.");
        }
        super.close();
    }
    
    public void resetImplicitWaitToDefault() {
        setImplicitWaitTo(DEFAULT_IMPLICIT_WAIT_SECONDS);
    }
    public void setImplicitWaitTo(long newTimeout) {
        manage().timeouts().implicitlyWait(newTimeout, TimeUnit.SECONDS);
    }


}
