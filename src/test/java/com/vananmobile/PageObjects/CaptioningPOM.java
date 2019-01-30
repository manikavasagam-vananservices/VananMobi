package com.vananmobile.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CaptioningPOM extends AccessingElement {

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(id = "getquote")
    private WebElement getQuoteElement;

    @FindBy(id = "emailquote")
    private WebElement emailMeQuoteElement;

    @FindBy(id = "proceedpayment")
    private WebElement proceedPaymentElement;

    @FindBy(id = "srclang_chosen")
    private WebElement sourceLanguageElement;

    @FindBy(id = "trglang_chosen")
    private WebElement targetLanguageElement;

    @FindBy(id = "formatting")
    private WebElement formattingElement;

    @FindBy(id = "hours")
    private WebElement hoursElement;

    @FindBy(id = "minutes")
    private WebElement minutesElement;

    @FindBy(id = "specification_pay_chosen")
    private WebElement specificationPayElement;

    @FindBy(id = "paytc_qemailcrm")
    private WebElement emailElement;

    @FindBy(id = "hlw_amt")
    private WebElement offerFeeElement;

    @FindBy(id = "trgtunitcost_disp")
    private WebElement basePriceElement;

    @FindBy(id = "trgt_tot")
    private WebElement totalUnitCostElement;

    @FindBy(id = "script_amount")
    private WebElement translationCostElement;

    @FindBy(id = "tcode_amount")
    private WebElement timeCodeElement;

    @FindBy(id = "sub_amt")
    private WebElement grandTotalElement;

    @FindBy(id = "trans_rate")
    private WebElement transactionFeeElement;

    @FindBy(id = "price_display")
    private WebElement orderTotalElement;

    @FindBy(id = "filecomments")
    private WebElement comments;

    @FindBy(id = "durationnewpay-0")
    private WebElement fileLength;

    @FindBy(id = "privacy_policy")
    private WebElement privacyPolicy;

    private Actions builder;
    private Action mouseOverHome;

    public CaptioningPOM(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void clickEmailMeGetQuote() {
        try {

            if (isElementDisplayed(emailMeQuoteElement)) {
                js.executeScript("$('#emailquote').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Get Email Me Quote button " + e);
        }
    }


    public void clickProceedPayment() {
        try {

            if (isElementDisplayed(proceedPaymentElement)) {
                js.executeScript("$('#proceedpayment').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Proceed payment button " + e);
        }
    }

    public void clickGetQuote() {
        try {

            if (isElementDisplayed(getQuoteElement)) {
                js.executeScript("$('#getquote').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Get Quote button " + e);
        }
    }

    public boolean isCustomMessageDisplayed() {

        return driver.findElement(By.xpath("//div[@class='qt_msg']"))
                .getText().contains("Customized rates apply");
    }

    public void selectSourceLanguage(String sourceLanguage) {
        WebElement sourceElement = driver.findElement(By.id("srclang-selectized"));
        sourceElement.sendKeys(sourceLanguage);
        sourceElement.sendKeys(Keys.RETURN);
        //selectDropDown(sourceElement, sourceLanguage);
        //enterTestBoxValues(sourceElement, sourceLanguage);
    }

    public void selectTargetLanguage(String targetLanguage) {
        WebElement targetElement = driver.findElement(By.id("trglang-selectized"));
        targetElement.sendKeys(targetLanguage);
        targetElement.sendKeys(Keys.RETURN);
        //selectDropDown(targetElement, targetLanguage);
        //enterTestBoxValues(hoursElement, targetLanguage);
    }

    public void selectFormatting(String categoryType) {
        WebElement formattingElement = driver.findElement(By.id("formatting"));
        selectDropDown(formattingElement, categoryType);
    }

    public void enterHours(String hour) {
        WebElement hoursElement = driver.findElement(By.id("hours"));
        enterTestBoxValues(hoursElement, hour);

    }

    public void enterMinutes(String minute) {
        WebElement minutesElement = driver.findElement(By.id("minutes"));
        enterTestBoxValues(minutesElement, minute);

    }

    public void selectSpecificationPay(String specificPay) {
        try {
            builder = new Actions(driver);
            mouseOverHome = builder.moveToElement(sourceLanguageElement).build();
            mouseOverHome.perform();
            clickElement(specificationPayElement);

            List<WebElement> spec = driver.findElements(By.xpath(
                    "//div[@id='specification_pay_chosen']/div/ul[@class='chosen-results']/li"));
            for (WebElement element : spec) {
                if (element.getText().equals(specificPay)) {
                    element.click();
                    break;
                }
            }
            /*selectDropDown(specificationPayElement, specificPay);
            driver.findElement(By.tagName("body")).click();*/
        } catch (Exception e) {
            System.out.println("Unable to select specification pay " + e);
        }
    }

    public void selectFreeTrialPage() {
        if (!driver.findElement(By.xpath(
                "//input[@type='checkbox' and @id='frtrial']"))
                .isSelected()) {

            js.executeScript("$('#frtrial').click();");
        }
    }

    public void selectNeedTranslation() {
        if (!driver.findElement(By.xpath(
                "//input[@type='checkbox' and @id='needtrc']"))
                .isSelected()) {

            js.executeScript("$('#needtrc').click();");
        }
    }

    public void deselectFreeTrialPage() {
        if (driver.findElement(By.xpath(
                "//input[@type='checkbox' and @id='frtrial']"))
                .isSelected()) {

            js.executeScript("$('#frtrial').click();");
        }
    }

    public void deselectNeedTranslation() {
        if (driver.findElement(By.xpath(
                "//input[@type='checkbox' and @id='needtrc']"))
                .isSelected()) {

            js.executeScript("$('#needtrc').click();");
        }
    }

    public double getOfferFee() {

        return convertAndGetValue(offerFeeElement);
    }

    public void enterEmailId(String email) {
        builder = new Actions(driver);
        mouseOverHome = builder.moveToElement(emailElement).build();
        mouseOverHome.perform();
        enterTestBoxValues(emailElement, email);
    }

    public double getBasePrice() {
        WebElement basePriceElement = driver.findElement(By.xpath("//span[@id='trgtunitcost_disp']"));
        return convertAndGetValue(basePriceElement);
    }

    public double getTranslationPrice() {
        WebElement translationCostElement = driver.findElement(By.xpath("//span[@id='script_amount']"));
        return convertAndGetValue(translationCostElement);
    }

    public double getTotalUnitCost() {
        WebElement totalUnitCostElement = driver.findElement(By.xpath("//span[@id='trgt_tot']"));
        return convertAndGetValue(totalUnitCostElement);
    }

    public double getTimeCodePrice() {
        WebElement timeCodeElement = driver.findElement(By.xpath("//span[@id='tcode_amount']"));
        return convertAndGetValue(timeCodeElement);
    }

    public double getGrandTotal() {
        WebElement grandTotalElement = driver.findElement(By.xpath("//span[@id='sub_amt']"));
        return convertAndGetValue(grandTotalElement);
    }

    public double getTransactionFee() {
        WebElement transactionFeeElement = driver.findElement(By.xpath("//span[@id='trans_rate']"));
        return convertAndGetValue(transactionFeeElement);
    }

    public double getOrderTotal() {
        WebElement orderTotalElement = driver.findElement(By.xpath("//span[@id='price_display']"));
        return convertAndGetValue(orderTotalElement);
    }

    public double convertAndGetValue(WebElement element) {

        if (element.isDisplayed()) {
            return Double.parseDouble(getElementValues(element));
        } else {
            return 0;
        }
    }

    public void enterComments(String comment) {
        try {
            enterTestBoxValues(comments, comment);
        } catch (Exception e) {
            System.out.println("Unable to enter a comment value " + e);
        }
    }

    public void enterFileLength(String length) {
        try {
            if (fileLength.isDisplayed()) {

                enterTestBoxValues(fileLength, length);
            }
        } catch (Exception e) {
            System.out.println("Unable to enter a file length value " + e);
        }
    }

    public void uploadFile(WebDriver driver, String fileName, String extenstion)
            throws IOException, AWTException, InterruptedException {

        fileName = fileName + extenstion;
        String filePath = System.getProperty("user.dir") + "/" + fileName;
        File file = new File(filePath);
        file.createNewFile();
        TimeUnit.SECONDS.sleep(10);
        WebElement fileUploadButton = driver.findElement(By.id("fileuploader"));
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

    public void clickPrivacyPolicy() {
        try {

            if (isElementDisplayed(privacyPolicy)) {
                clickElement(privacyPolicy);
            }
        } catch (Exception e) {
            System.out.println("Unable to click privacy policy " + e);
        }
    }

    public String getToolTipMessage() {

        return driver.findElement(By.xpath("//div[contains(@role,'tooltip')]/div[@class='tooltip-inner']")).getText();
    }
}

