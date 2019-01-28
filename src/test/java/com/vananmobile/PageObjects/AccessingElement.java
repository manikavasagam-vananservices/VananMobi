package com.vananmobile.PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AccessingElement {


    public void enterTestBoxValues(WebElement element, String content) {

        element.clear();
        element.sendKeys(content);
    }

    public void clickElement(WebElement element) {

        element.click();
    }

    public void clickElements(WebElement element) throws NoSuchElementException {

        element.click();
    }

    public void selectDropDown(WebElement element, String content) {

        Select dropDown = new Select(element);
        dropDown.selectByVisibleText(content);
    }

    public String getElementValues(WebElement element) {

        return element.getText();
    }

    public String getAttributeValues(WebElement element, String content) {

        return element.getAttribute(content);
    }

    public boolean isElementDisplayed(WebElement element) {

        return element.isDisplayed();
    }

    public void uploadFile(WebDriver driver, String fileName, String extenstion)
            throws IOException, AWTException, InterruptedException {

        fileName = fileName + extenstion;
        String filePath = System.getProperty("user.dir") + "/" + fileName;
        File file = new File(filePath);
        file.createNewFile();
        TimeUnit.SECONDS.sleep(10);
        WebElement fileUploadButton = driver.findElement(By.className("ui-upolad"));
        fileUploadButton.click();
        StringSelection selection = new StringSelection(fileName);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void pageRefresh(WebDriver driver) {

        driver.navigate().refresh();
    }

}
