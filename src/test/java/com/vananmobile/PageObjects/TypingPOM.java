package com.vananmobile.PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class TypingPOM extends AccessingElement {

    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(id = "getquote")
    private WebElement getQuoteElement;

    @FindBy(id = "emailquote")
    private WebElement emailMeQuoteElement;

    @FindBy(id = "proceedpayment")
    private WebElement proceedPaymentElement;

    @FindBy(id = "filecomments")
    private WebElement commentsElement;

    @FindBy(id = "durationnewpay-0")
    private WebElement fileLengthElement;

    @FindBy(id = "sourcefiletype")
    private WebElement selectFileTypeElement;

    @FindBy(id = "pagecount")
    private WebElement pageCountElement;

    @FindBy(id = "srclang")
    private WebElement selectSourceLanguageFromElement;

    @FindBy(id = "catetype")
    private WebElement categoryElement;

    @FindBy(id = "formatting")
    private WebElement formattingElement;

    @FindBy(id = "mailcountry")
    private WebElement mailCountryElement;

    @FindBy(id = "prfilelength")
    private WebElement minuteElement;

    @FindBy(id = "qttcodecrm")
    private WebElement timeCodeElement;

    @FindBy(id = "paytc_qemailcrm")
    private WebElement emailElement;

    @FindBy(id = "trgtunitcost_disp")
    private WebElement basePriceElement;

    @FindBy(id = "trgt_tot")
    private WebElement unitCostElement;

    @FindBy(id = "nota_sub_amt")
    private WebElement notaryElement;

    @FindBy(id = "notapro_sub_amt")
    private WebElement processingFeeElement;

    @FindBy(id = "mfile_sub_amt")
    private WebElement mailingFeeElement;

    @FindBy(id = "sub_amt")
    private WebElement grandTotalElement;

    @FindBy(id = "trans_rate")
    private WebElement transactionFeeElement;

    @FindBy(id = "price_display")
    private WebElement orderTotalElement;

    @FindBy(id = "trctcodecost_disp")
    private WebElement timeCodePriceElement;

    @FindBy(id = "trlverbacost_disp")
    private WebElement verbatimElement;

    @FindBy(id = "privacy_policy")
    private WebElement privacyPolicy;

    public TypingPOM(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void selectFileType(String fileType) {

        selectDropDown(selectFileTypeElement, fileType);
    }

    public void enterPageCount(String pageCounts) {

        pageCountElement.sendKeys(pageCounts);
        pageCountElement.sendKeys(Keys.BACK_SPACE);
    }

    public void enterMinutes(String minute) {
        try {
            enterTestBoxValues(minuteElement, minute);
        } catch (Exception e) {
            System.out.println("Unable to enter a minute value " + e);
        }
    }

    public void selectLanguageFrom(String sourceLanguage) {
        selectSourceLanguageFromElement = driver.findElement(By.id("srclang"));
        selectSourceLanguageFromElement.sendKeys(sourceLanguage);
    }

    public void selectCategory(String categoryType) {

        selectDropDown(categoryElement, categoryType);
    }

    public void selectFormatting(String formatting) {

        selectDropDown(formattingElement, formatting);
    }

    public void selectTimeCode(String timeCode) {

        selectDropDown(timeCodeElement, timeCode);
    }

    public void selectNotarization() {

        if (!driver.findElement(By.id("notacrmpay"))
                .isSelected()) {

            js.executeScript("$('#notacrmpay').click();");
        }
    }

    public void selectVerbatim() {

        if (!driver.findElement(By.id("qtvercrmpay-lbl"))
                .isSelected()) {

            js.executeScript("$('#qtvercrmpay-lbl').click();");
        }
    }

    public void selectUSNativeTranscriber() {

        if (!driver.findElement(By.id("nativespkr-lbl"))
                .isSelected()) {

            js.executeScript("$('#nativespkr-lbl').click();");
        }
    }

    public void selectRequestMailCopy(String country, String address) {

        if (!driver.findElement(By.id("mailfilecrmpay"))
                .isSelected()) {

            js.executeScript("$('#mailfilecrmpay').click();");
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ex) {

        }

        try {
            selectDropDown(mailCountryElement, country);
            driver.findElement(By.tagName("body")).click();

            driver.findElement(By.id("paytc_mailaddress")).sendKeys(address);
        } catch (Exception e) {
            System.out.println("Unable to select mail country " + e);
        }
    }

    public void enterEmailId(String email) {

        enterTestBoxValues(emailElement, email);
    }

    public double getBasePrice() {
        basePriceElement = driver.findElement(By.xpath("//span[@id='trgtunitcost_disp']"));
        return convertAndGetValue(basePriceElement);
    }


    public double getUnitCost() {
        unitCostElement = driver.findElement(By.xpath("//span[@id='trgt_tot']"));
        return convertAndGetValue(unitCostElement);
    }

    public double getNotary() {

        return convertAndGetValue(notaryElement);
    }

    public double getProcessingFee() {

        return convertAndGetValue(processingFeeElement);
    }

    public double getTimeCode() {

        return convertAndGetValue(timeCodePriceElement);
    }

    public double getVerbatim() {

        return convertAndGetValue(verbatimElement);
    }

    public double getMailingFee() {

        return convertAndGetValue(mailingFeeElement);
    }

    public double getGrandTotal() {
        grandTotalElement = driver.findElement(By.xpath("//span[@id='sub_amt']"));
        return convertAndGetValue(grandTotalElement);
    }

    public double getTransactionFee() {
        transactionFeeElement = driver.findElement(By.xpath("//span[@id='trans_rate']"));
        return convertAndGetValue(transactionFeeElement);
    }

    public double getOrderTotal() {
        orderTotalElement = driver.findElement(By.xpath("//span[@id='price_display']"));
        return convertAndGetValue(orderTotalElement);
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


    public double convertAndGetValue(WebElement element) {

        if (element.isDisplayed()) {
            return Double.parseDouble(getElementValues(element));
        } else {
            return 0;
        }
    }

    public void enterComments(String comment) {
        try {
            enterTestBoxValues(commentsElement, comment);
        } catch (Exception e) {
            System.out.println("Unable to enter a comment value " + e);
        }
    }

    public void enterFileLength(String length) {
        try {
            if (fileLengthElement.isDisplayed()) {

                enterTestBoxValues(fileLengthElement, length);
            }
        } catch (Exception e) {
            System.out.println("Unable to enter a file length value " + e);
        }
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
