package com.vananmobile.Testcases.DirectPayment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.vananmobile.Common.AppiumServerD;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vananmobile.Common.TestBase;

import com.vananmobile.PageObjects.TranslationPOM;
import org.apache.maven.shared.utils.io.FileUtils;
public class TranslationL1 extends TestBase {

    private static String username = "";
    private static String password = "";

    private String service = "Translation";

    private static List<String> sourceLanguages = new ArrayList<String>();
    private static List<String> targetLanguages = new ArrayList<String>();
    private static List<Double> unitCosts = new ArrayList<Double>();
    private static List<String> processings = new ArrayList<String>();
    private static List<Double> pages = new ArrayList<Double>();

    private double totalUnitCost = 0;
    private double grandtotal = 0;
    private double transcationCost = 0;
    private double orderCost = 0;

    private String unitStatus = "";
    private String totalUnitStatus = "";
    private String grandtotalStatus = "";
    private String transcationStatus = "";
    private String orderStatus = "";
    AppiumServerD appiumServerD = new AppiumServerD();

    @Test
    public void testStep() throws Exception {


        sheet = workbook.getSheetAt(0);

       // translation.enterDocumentType("testing");

        for (int i = 0; i < 2; i++) {
            //driver.get(System.getProperty("website"));
            driver.get("https://vananservices.com/Translation-Quote.php");

            TranslationPOM translation = new TranslationPOM(driver);
            translation.selectFileType("Document");
            System.out.print("S.No " + (i + 1));
            System.out.print("Source language : " + sourceLanguages.get(i));
            System.out.print("Target language : " + targetLanguages.get(i));
            System.out.print("Unit costs : " + unitCosts.get(i));
            System.out.print("Processings : " + processings.get(i));

            translation.pageCount(pages.get(i).intValue()+"");
            translation.selectLanguageFrom(sourceLanguages.get(i));
            translation.selectLanguageTo(targetLanguages.get(i));
            takeSnapShot(driver, "Transcription"+i+".jpg");
            String currentUrl = "";
            if (!processings.get(i).equalsIgnoreCase("Request")) {

                totalUnitCost = pages.get(i) * unitCosts.get(i);
                grandtotal = totalUnitCost;
                transcationCost = grandtotal * 0.05;
                orderCost = grandtotal + transcationCost;

                unitStatus = checkStatus(unitCosts.get(i), translation.getActualCost(), "Unit cost");
                totalUnitStatus = checkStatus(totalUnitCost, translation.getTranslationCost(), "Total Unit cost");
                grandtotalStatus = checkStatus(grandtotal, translation.getSubTotal(), "Grand Total");
                transcationStatus = checkStatus(transcationCost, translation.getTransactionFee(), "Transaction fee");
                orderStatus = checkStatus(orderCost, translation.getGrandTotal(), "Order total");

                cell = sheet.getRow(i + 1).getCell(5);
                cell.setCellValue(totalUnitCost);
                cell = sheet.getRow(i + 1).getCell(6);
                cell.setCellValue(grandtotal);
                cell = sheet.getRow(i + 1).getCell(7);
                cell.setCellValue(transcationCost);
                cell = sheet.getRow(i + 1).getCell(8);
                cell.setCellValue(orderCost);
                cell = sheet.getRow(i + 1).getCell(9);
                cell.setCellValue(unitStatus);
                cell = sheet.getRow(i + 1).getCell(10);
                cell.setCellValue(totalUnitStatus);
                cell = sheet.getRow(i + 1).getCell(11);
                cell.setCellValue(grandtotalStatus);
                cell = sheet.getRow(i + 1).getCell(12);
                cell.setCellValue(transcationStatus);
                cell = sheet.getRow(i + 1).getCell(13);
                cell.setCellValue(orderStatus);
                cell = sheet.getRow(i + 1).getCell(15);

                if (unitStatus.equals("Pass") && totalUnitStatus.equals("Pass") && grandtotalStatus.equals("Pass")
                        && transcationStatus.equals("Pass") && orderStatus.equals("Pass")) {

                    cell.setCellValue("Pass");
                } else {
                    cell.setCellValue("Fail");
                }
            }
            System.out.println();
        }
        workbook.write(fileOutput);
        fileOutput.close();
    }

    private String checkStatus(double data1, double data2, String message) {
        String status;
        System.out.println(message);
        if (data1 == data2) {
            System.out.print(": Pass\n");
            status = "Pass";
        } else {
            System.out.print(": Fail\n");
            System.out.println("Expected : " + data1);
            System.out.println("Actual : " + data2);
            status = "Fail\n" + "Expected : " + data1 + "\nActual : " + data2;
        }
        return status;
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("deviceName", "33001f8921b473eb");
        capabilities.setCapability("browserName", MobileBrowserType.CHROME);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60*2, TimeUnit.SECONDS);*/
        if(!appiumServerD.checkIfServerIsRunnning()) {
            appiumServerD.startServer();
        } else {
            System.out.println("Appium Server already running on Port - 4723");
        }
        super.setCapabilities();
        readTranslateData();
        fileOutput = new FileOutputStream(file);
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
        if(appiumServerD.checkIfServerIsRunnning()) {
            appiumServerD.stopServer();
        }  else {
            System.out.println("Appium Server already stopped on Port - 4723");
        }
    }

    public static void readTranslateData() throws IOException {

        file = new File("src/test/resources/Translation/TranslationL1.xls");
        fileInput = new FileInputStream(file);
        workbook = new HSSFWorkbook(fileInput);
        sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            cell = sheet.getRow(i).getCell(0);
            sourceLanguages.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(1);
            targetLanguages.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(2);
            unitCosts.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(3);
            processings.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(4);
            pages.add(cell.getNumericCellValue());
        }
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{

        //TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        String imageName =System.currentTimeMillis()+ ".png";
        File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("D://"+fileWithPath));
        /*File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile=new File(System.getProperty("user.dir") +"/"+fileWithPath);

        FileUtils.copyFile(SrcFile, DestFile);*/
    }
}
