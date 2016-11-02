package org.ccTest.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/org/ccTest/test"},

        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-html-report/",
                "json:target/cucumber-json-report/cucumberTestReport.json",
                "junit:target/cucumber-junit-report/cucumberTestReport.xml"},
        tags = {},
        glue = {"org.ccTest.common", "org.ccTest.test"}
        )

public class RunCukeTest {

}
