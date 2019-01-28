package com.vananmobile.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TranscriptionPOM extends AccessingElement {

    private WebDriver driver;
    private JavascriptExecutor js;
    @FindBy(id = "hours")
    private WebElement hours;
    @FindBy(id = "minutes")
    private WebElement minutes;
    @FindBy(id = "srclang")
    private WebElement sourceLang;
    @FindBy(id = "qttcode")
    private WebElement timeCode;
    @FindBy(id = "trcpriceb_disp")
    private WebElement basePrice;
    @FindBy(id = "hlw_amt")
    private WebElement discountPrice;
    @FindBy(id = "off-lbl")
    private WebElement discountActualPrice;
    @FindBy(id = "trccostb_disp")
    private WebElement uintCost;
    @FindBy(id = "trcverbacost_disp")
    private WebElement verbatim;
    @FindBy(id = "trctcodecost_disp")
    private WebElement timeCodePrice;
    @FindBy(id = "expprice_disp")
    private WebElement expediatedPrice;
    @FindBy(id = "paytc_qemailcrm")
    private WebElement emailElement;
    @FindBy(id = "qcheckcost_disp")
    private WebElement additionalQtyCheckPrice;
    @FindBy(id = "sub_amt")
    private WebElement totalCost;
    @FindBy(id = "trctotcost_disp")
    private WebElement esubtotalCost;
    @FindBy(id = "hlw_amt")
    private WebElement offerDiscount;
    @FindBy(id = "trans_rate")
    private WebElement transactionFeeElement;
    @FindBy(id = "price_display")
    private WebElement grandTotal;
    @FindBy(xpath = "//i[@class='fa fa-file']")
    private WebElement fileIcon;
    @FindBy(xpath = "//i[@class='fa fa-language']")
    private WebElement languageIcon;
    @FindBy(xpath = "//i[@class='fa fa-list-alt']")
    private WebElement categoryIcon;
    @FindBy(xpath = "//i[@class='fa fa-font']")
    private WebElement verbatimIcon;
    @FindBy(xpath = "//i[@class='fa fa-clock-o']")
    private WebElement timecodeIcon;
    @FindBy(xpath = "//i[@class='fa fa-check-circle']")
    private WebElement additionalQtyIcon;
    @FindBy(xpath = "//i[@class='fa fa-tags']")
    private WebElement freeTrailIcon;
    @FindBy(id = "emailquote")
    private WebElement emailMeQuote;
    @FindBy(id = "proceedpayment")
    private WebElement proceedPayment;
    @FindBy(id = "getquote")
    private WebElement getQuote;
    @FindBy(id = "durationnewpay-0")
    private WebElement fileLength;
    @FindBy(id = "filecomments")
    private WebElement comments;
    @FindBy(id = "privacy_policy")
    private WebElement privacyPolicy;

    public TranscriptionPOM(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void selectPersonal() {

        js.executeScript("$('#prpse1').click();");
    }

    public void selectBusiness() {

        js.executeScript("$('#prpse0').click();");
    }

    public void enterHours(String hour) {

        WebElement hours = driver.findElement(By.id("hours"));
        enterTestBoxValues(hours, hour);
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

    public void enterComments(String comment) {
        try {
            enterTestBoxValues(comments, comment);
        } catch (Exception e) {
            System.out.println("Unable to enter a comment value " + e);
        }
    }

    public void enterMinutes(String minute) {

        WebElement minutes = driver.findElement(By.id("minutes"));
        enterTestBoxValues(minutes, minute);
    }

    public void selectLanguageFrom(String sourceLanguage) {

        WebElement minutes = driver.findElement(By.id("srclang"));
        minutes.sendKeys(sourceLanguage);
        //enterTestBoxValues(sourceLang, sourceLanguage);
        //selectDropDown(sourceLang, sourceLanguage);
        // driver.findElement(By.tagName("body")).click();
    }

    public void selectTimeCode(String timecode) {
        WebElement timeCode = driver.findElement(By.id("qttcodecrm"));
        selectDropDown(timeCode, timecode);
    }

    public void selectFreeTrail() {
        if (!driver.findElement(By.id("ft1"))
                .isSelected()) {

            js.executeScript("$('#ft1').click();");
        }
    }

    public void selectNativeSpeaker() {
        if (!driver.findElement(By.xpath(
                "//input[@type='checkbox' and @id='nativespkr']"))
                .isSelected()) {

            js.executeScript("$('#nativespkr').click();");
        }
    }

    public void selectTranslation(int optionValue) {
        switch (optionValue) {
            case 0:
                if (driver
                        .findElement(By.xpath(
                                "//input[@type='checkbox' and @id='needtranslation']"))
                        .isSelected()) {

                    js.executeScript("$('#needtranslation').click();");
                }
                break;
            case 1:
                if (!driver.findElement(By.xpath("//input[@id='needtranslation']"))
                        .isSelected()) {

                    js.executeScript("$('#needtranslation').click();");
                }
                break;
        }
    }

    public void selectVerbatim(int optionValue) {
        switch (optionValue) {
            case 0:
                if (!driver.findElement(By.xpath("//input[@id='cleanverba']"))
                        .isSelected()) {

                    js.executeScript("$('#cleanverba').click();");
                }
                break;
            case 1:
                if (!driver.findElement(By.xpath("//input[@id='fullverba']"))
                        .isSelected()) {

                    js.executeScript("$('#fullverba').click();");
                }
                break;
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

    public void selectCategory(String optionValue) {
        WebElement category = driver.findElement(By.id("catetype"));
        selectDropDown(category, optionValue);
    }

    public void selectSpeakerCount(String optionValue) {
        WebElement category = driver.findElement(By.id("speakercnt"));
        selectDropDown(category, optionValue);
    }

    public void clickNextButton() {
        WebElement nextBtn = driver.findElement(By.xpath("//div[@id='next']"));
        clickElement(nextBtn);
    }

    public void selectAdditionalQtyCheck(int additionalQtyCheck) {

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

    public double getBasePrice() {

        WebElement basePrice = driver.findElement(By.xpath("//span[@id='trcpriceb_disp']"));
        return convertAndGetValue(basePrice);
    }

    public double getDiscountPrice() {

        return convertAndGetValue(discountPrice);
    }

    public double getActualDiscountPrice() {

        return convertAndGetValue(discountActualPrice);
    }


    public double getUnitCost() {
        WebElement uintCost = driver.findElement(By.xpath("//span[@id='trccostb_disp']"));
        return convertAndGetValue(uintCost);
    }

    public double getVerbatim() {

        return convertAndGetValue(verbatim);
    }

    public double getTimeCode() {
        WebElement timeCodePrice = driver.findElement(By.xpath("//span[@id='trctcodecost_disp']"));
        return convertAndGetValue(timeCodePrice);
    }

    public double getAddtionalQtyCheck() {

        return convertAndGetValue(additionalQtyCheckPrice);
    }

    public double getTotalCost() {
        WebElement totalCost = driver.findElement(By.xpath("//span[@id='sub_amt']"));
        return convertAndGetValue(totalCost);
    }

    public double getESsubTotalCost() {

        //return convertAndGetValue(esubtotalCost);
        return 0;
    }

    public double getTranscationFee() {
        WebElement transactionFeeElement = driver.findElement(By.xpath("//span[@id='trans_rate']"));
        return convertAndGetValue(transactionFeeElement);
    }

    public double getOfferDiscout() {

        return convertAndGetValue(offerDiscount);
    }

    public double getGrandTotal() {
        WebElement grandTotal = driver.findElement(By.xpath("//span[@id='price_display']"));
        return convertAndGetValue(grandTotal);
    }

    public double getExpedite() {

        return convertAndGetValue(expediatedPrice);
    }

    public double convertAndGetValue(WebElement element) {

        if (element.isDisplayed()) {
            return Double.parseDouble(getElementValues(element));
        } else {
            return 0;
        }
    }

    public void pageRefresh() {

        driver.navigate().refresh();
    }

    public boolean isFileIconDisplayed() {
        return isElementDisplayed(fileIcon);
    }

    public boolean isLangIconDisplayed() {
        return isElementDisplayed(languageIcon);
    }

    public boolean isCategoryIconDisplayed() {
        return isElementDisplayed(categoryIcon);
    }

    public boolean isVerbatimIconDisplayed() {
        return isElementDisplayed(verbatimIcon);
    }

    public boolean isTimeCodeIconDisplayed() {
        return isElementDisplayed(timecodeIcon);
    }

    public boolean isFreeTrailIconDisplayed() {
        return isElementDisplayed(freeTrailIcon);
    }

    public boolean isAdditionalQtyIconDisplayed() {
        return isElementDisplayed(additionalQtyIcon);
    }

    public boolean isUSNativeSpeakerDisplayed() {
        return isElementDisplayed(driver.findElement(By.id("nativespkr-lbl")));
    }

    public boolean isTranslationDisplayed() {
        return isElementDisplayed(
                driver.findElement(By.id("needtranslation-lbl")));
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

    public void enterEmailId(String email) {

        Actions builder = new Actions(driver);
        Action mouseOverHome = builder.moveToElement(emailElement).build();
        mouseOverHome.perform();
        enterTestBoxValues(emailElement, email);
    }

    public String getUploadedFileName() {

        return driver.findElement(By.xpath("//div[@id='fileuploadfield']"
                + "/div/div[2]")).getText();
    }

    public boolean isCustomMessageDisplayed() {

        return driver.findElement(By.xpath("//div[@class='qt_msg ui-msg']"))
                .getText().contains("Customized rate apply for");
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

        WebElement element = driver.findElement(By.xpath("//div[contains(@role,'tooltip')]/div[@class='tooltip-inner']"));
        Actions builder = new Actions(driver);
        Action mouseOverHome = builder.moveToElement(element).build();
        mouseOverHome.perform();
        return element.getText();
    }
}
