package com.vananmobile.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

public class TranslationPOM extends AccessingElement {

    private WebDriver driver;
    private JavascriptExecutor js;

    //UI action performing Element
    private WebElement selectFileTypeElement;
    private WebElement documentElement;
    private WebElement pageCountElement;
    private WebElement minuteElement;
    private WebElement hourElement;
    private WebElement selectSourceLanguageFromElement;
    private WebElement selectSourceLanguageToElement;
    private WebElement fileCommentElement;
    private WebElement mailCountry;
    private WebElement comments;
    private WebElement emailMeQuote;
    private WebElement getQuote;
    private WebElement fileLength;

    //UI payment Element
    private WebElement actualCost;
    private WebElement expeditedCost;
    private WebElement notaryFee;
    private WebElement certificationFeeElement;
    private WebElement processFee;
    private WebElement proceedPayment;
    private WebElement privacyPolicy;
    private WebElement subTotalElement;
    private WebElement translationCostElement;
    private WebElement transactionFeeElement;
    private WebElement additionalQualityAmountElement;
    private WebElement mailingFeeElement;
    private WebElement offerFeeElement;
    private WebElement grandTotalElement;


    public TranslationPOM(WebDriver driver) {

        this.driver = driver;
        js = (JavascriptExecutor) driver;
        //PageFactory.initElements(driver, this);
    }

    public void selectFileType(String fileType) {

        selectFileTypeElement = driver.findElement(By.xpath("//div[@id='first5']//..//select[@id='sourcefiletype']"));
        selectFileTypeElement.sendKeys(fileType);
          /*  selectDropDown(selectFileTypeElement, fileType);
            driver.findElement(By.tagName("body")).click();*/
    }

    public void enterDocumentType(String dname) {
        documentElement = driver.findElement(By.xpath(""));
        enterTestBoxValues(documentElement, dname);
    }

    public void pageCount(String pageCount) {
        pageCountElement = driver.findElement(By.id("pagecount"));
        enterTestBoxValues(pageCountElement, pageCount);
    }

    public void minutes(String minute) {
        minuteElement = driver.findElement(By.xpath(""));
        enterTestBoxValues(minuteElement, minute);
    }

    public void hours(String hour) {
        hourElement = driver.findElement(By.xpath(""));
        enterTestBoxValues(hourElement, hour);
    }

    public void selectLanguageFrom(String sourceLanguage) {

        selectSourceLanguageFromElement = driver.findElement(By.id("srclang"));
        selectSourceLanguageFromElement.sendKeys(sourceLanguage);
        //enterTestBoxValues(selectSourceLanguageFromElement, sourceLanguage);
        // selectDropDown(selectSourceLanguageFromElement, sourceLanguage);
    }

    public void selectLanguageTo(String targetLanguage) {

        selectSourceLanguageToElement = driver.findElement(By.id("trglang"));
        selectSourceLanguageToElement.sendKeys(targetLanguage);
        //enterTestBoxValues(selectSourceLanguageToElement, targetLanguage);
        //selectDropDown(selectSourceLanguageToElement, targetLanguage);
    }

    /*public void fileComments(String message) {

        enterTestBoxValues(fileCommentElement, message);
    }

    public void selectTurnaroundTime(int optionValue) {

        switch (optionValue) {
            case 0:

                if ((driver.findElement(By.id("rushopts0"))).isSelected()) {
                    break;
                } else {
                    clickElement(driver.findElement(By.id("rushopts0")));
                    break;
                }
            case 1:
                clickElement(driver.findElement(By.id("rushopts1")));
                break;
            case 2:
                clickElement(driver.findElement(By.id("rushopts2")));
                break;
            case 3:
                clickElement(driver.findElement(By.id("rushopts3")));
                break;
        }

    }

    public void selectFreeTrail() {
        if (!driver.findElement(By.id("frtrial1"))
                .isSelected()) {

            js.executeScript("$('#frtrial1').click();");
        }
    }

    public void emailId(String emailId) {

        enterTestBoxValues(driver.findElement(By.id("paytc_qemailcrm")), emailId);
    }

    public void clickEmailQuote() {

        clickElement(driver.findElement(By.id("emailquote")));
    }

    public void clickAdditionalQtyCheck() {

        clickElement(driver.findElement(By.id("qcheck")));
    }

    public void clickCertificationLanguage() {

        clickElement(driver.findElement(By.id("certlang")));
    }

    public void selectRequestMailCopy(String country, String address) {

        if (!driver.findElement(By.id("mailfilecrmpay1"))
                .isSelected()) {

            js.executeScript("$('#mailfilecrmpay1').click();");
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException ex) {

        }

        try {
            selectDropDown(mailCountry, country);
            driver.findElement(By.tagName("body")).click();
            driver.findElement(By.id("paytc_mailaddress")).clear();
            driver.findElement(By.id("paytc_mailaddress")).sendKeys(address);
        } catch (Exception e) {
            System.out.println("Unable to select mail country " + e);
        }
    }*/

    public double getActualCost() {

        actualCost = driver.findElement(By.xpath("//span[@id='trgtunitcost_disp']"));
        return convertAndGetValue(actualCost);
    }

    public double getExpeditedCost() {

        expeditedCost = driver.findElement(By.xpath("//span[@id='expd_sub_amt']"));
        return convertAndGetValue(expeditedCost);
    }

    public double getNotaryFee() {

        notaryFee = driver.findElement(By.xpath("//span[@id='nota_sub_amt']"));
        return convertAndGetValue(notaryFee);
    }

    public double getProcessFee() {

        processFee = driver.findElement(By.xpath("//span[@id='notapro_sub_amt']"));
        return convertAndGetValue(processFee);
    }

    public double getTranslationCost() {

        translationCostElement = driver.findElement(By.xpath("//span[@id='trgt_tot']"));
        return convertAndGetValue(translationCostElement);
    }

    public double getSubTotal() {

        subTotalElement = driver.findElement(By.xpath("//span[@id='sub_amt']"));
        return convertAndGetValue(subTotalElement);
    }

    public double getTransactionFee() {

        transactionFeeElement = driver.findElement(By.xpath("//span[@id='trans_rate']"));
        return convertAndGetValue(transactionFeeElement);
    }

    public double getAdditionalQualityAmount() {

        additionalQualityAmountElement = driver.findElement(By.xpath("//span[@id='qcheck_sub_amt']"));
        return convertAndGetValue(additionalQualityAmountElement);
    }

    public double getCertificationFee() {

        certificationFeeElement = driver.findElement(By.xpath("//span[@id='cert_sub_amt']"));
        return convertAndGetValue(certificationFeeElement);
    }

    public double getMailingFee() {

        mailingFeeElement = driver.findElement(By.xpath("//span[@id='mfile_sub_amt']"));
        return convertAndGetValue(mailingFeeElement);
    }

    public double getOfferFee() {

        offerFeeElement = driver.findElement(By.xpath("//span[@id='hlw_amt']"));
        return convertAndGetValue(offerFeeElement);
    }

    public double getGrandTotal() {

        grandTotalElement = driver.findElement(By.xpath("//span[@id='price_display']"));
        return convertAndGetValue(grandTotalElement);
    }

    public double convertAndGetValue(WebElement element) {

        if (element.isDisplayed()) {
            return Double.parseDouble(getElementValues(element));
        } else {
            return 0;
        }
    }

    /*public void selectAdditionalQtyCheck(int additionalQtyCheck) {

        switch (additionalQtyCheck) {
            case 0:

                if (!driver.findElement(By.xpath("//input[@id='qc0']"))
                        .isSelected()) {

                    js.executeScript("$('#qc0').click();");
                }
                break;
            case 1:
                if (!driver.findElement(By.xpath("//input[@id='qc1']"))
                        .isSelected()) {

                    js.executeScript("$('#qc1').click();");
                }
                break;

        }
    }

    public void selectNeedTranscript(int status) {

        switch (status) {
            case 0:

                if (!driver.findElement(By.xpath("//input[@id='needtrc1']"))
                        .isSelected()) {

                    js.executeScript("$('#needtrc1').click();");
                }
                break;
            case 1:
                if (!driver.findElement(By.xpath("//input[@id='needtrc0']"))
                        .isSelected()) {

                    js.executeScript("$('#needtrc0').click();");
                }
                break;

        }
    }

    public void selectTimeCode(int status) {

        switch (status) {
            case 0:

                if (!driver.findElement(By.xpath("//input[@id='tcode1']"))
                        .isSelected()) {

                    js.executeScript("$('#tcode1').click();");
                }
                break;
            case 1:
                if (!driver.findElement(By.xpath("//input[@id='tcode0']"))
                        .isSelected()) {

                    js.executeScript("$('#tcode0').click();");
                }
                break;

        }
    }

    public void selectNativeSpeaker() {

        if (!driver.findElement(By.xpath("//input[@id='nativespkr']"))
                .isSelected()) {

            js.executeScript("$('#nativespkr').click();");
        }
    }

    public void selectCertification() {

        if (!driver.findElement(By.id("certlang"))
                .isSelected()) {

            js.executeScript("$('#certlang').click();");
        }
    }

    public void selectNotarization() {

        if (!driver.findElement(By.id("notrpay"))
                .isSelected()) {

            js.executeScript("$('#notrpay').click();");
        }
    }

    public void enterComments(String comment) {
        try {
            enterTestBoxValues(comments, comment);
        } catch (Exception e) {
            System.out.println("Unable to enter a comment value " + e);
        }
    }


    public void selectTAT(int optionValue) {

        switch (optionValue) {
            case 0:

                if (!driver.findElement(By.xpath("//input[@id='rushopts0']"))
                        .isSelected()) {

                    js.executeScript("$('#rushopts0').click();");
                }
                break;
            case 1:

                if (!driver.findElement(By.xpath("//input[@id='rushopts1']"))
                        .isSelected()) {

                    js.executeScript("$('#rushopts1').click();");
                }
                break;
            case 2:

                if (!driver.findElement(By.xpath("//input[@id='rushopts2']"))
                        .isSelected()) {

                    js.executeScript("$('#rushopts2').click();");
                }
                break;
            case 3:

                if (!driver.findElement(By.xpath("//input[@id='rushopts3']"))
                        .isSelected()) {

                    js.executeScript("$('#rushopts3').click();");
                }
                break;
            case 4:

                if (!driver.findElement(By.xpath("//input[@id='deliveryReq']"))
                        .isSelected()) {

                    js.executeScript("$('#deliveryReq').click();");
                }
                break;
            case 5:

                if (!driver.findElement(By.xpath("//input[@id='expedited']"))
                        .isSelected()) {

                    js.executeScript("$('#expedited').click();");
                }
                break;
        }
    }

    public void clickEmailMeGetQuote() {
        try {

            if (isElementDisplayed(emailMeQuote)) {
                js.executeScript("$('#emailquote').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Get Email Me Quote button " + e);
        }
    }


    public void clickProceedPayment() {
        try {

            if (isElementDisplayed(proceedPayment)) {
                js.executeScript("$('#proceedpayment').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Proceed payment button " + e);
        }
    }

    public void clickGetQuote() {
        try {

            if (isElementDisplayed(getQuote)) {
                js.executeScript("$('#getquote').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click Get Quote button " + e);
        }
    }

    public void clickPrivacyPolicy() {
        try {

            if (isElementDisplayed(privacyPolicy)) {
                //clickElement(privacyPolicy);
                js.executeScript("$('#privacy_policy').click();");
            }
        } catch (Exception e) {
            System.out.println("Unable to click privacy policy " + e);
        }
    }

    public String getToolTipMessage() {

        return driver.findElement(By.xpath("//div[contains(@role,'tooltip')]/div[@class='tooltip-inner']")).getText();
    }

    public boolean isCustomMessageDisplayed() {
        boolean status = false;
        try {
            status = driver.findElement(By.xpath("//div[@class='qt_msg ui-msg']"))
                    .getText().contains("Customized rate apply for");
        } catch (Exception ex) {
            status = false;
        }
        return status;
    }

    public void enterFileLength(String length) {
        try {
            if (fileLength.isDisplayed()) {

                enterTestBoxValues(fileLength, length);
            }
        } catch (Exception e) {
            System.out.println("Unable to enter a file length value " + e);
        }
    }*/
}
