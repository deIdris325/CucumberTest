package org.ccTest.test.stepdef;

import cucumber.api.java.en.Then;
import org.ccTest.common.stepdef.BasicStepdefs;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VerifyImageStepdefs extends BasicStepdefs {

    @Then("^verify image text \"([^\"]*)\"$")
    public void verifyImageText(String imgid) throws Throwable {
        WebElement imgDivElement = driver.findElement(By.id("bodyContent"));
        String img_text = imgDivElement.findElement(By.tagName("img")).getAttribute("alt");
        Assert.assertEquals(img_text, imgid);

    }

    public Boolean selectByText(String text) {
        WebElement tab = driver.findElement(By.className("rutscheV"));
        List<WebElement> allTexts = tab.findElements(By.tagName("h2"));

        for (WebElement we : allTexts) {
            if (we.getText().equals(text))
                System.out.print("\n##<is text: " + we.getText());
                System.out.print("\n##>should text: " + text);
        }
        return true;
    }

    @Then("^the image is \"([^\"]*)\" height and \"([^\"]*)\" width")
    public void imageSize(String searchimgHeight, String searchimgWidth) throws Throwable {
        WebElement imgElement = driver.findElement(By.className("group"));

        String imgisHeight = imgElement.findElement(By.tagName("img")).getAttribute("height");
        String imgiswidth = imgElement.findElement(By.tagName("img")).getAttribute("width");

        boolean jo = imgisHeight.equals(searchimgHeight);
        boolean ja = imgiswidth.equals(searchimgWidth);

        Assert.assertTrue(jo);
        Assert.assertTrue(ja);

    }


}
