package com.vananmobile.Testcases.DirectPayment;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vananmobile.Common.TestBase;

import com.vananmobile.PageObjects.CaptioningPOM;

public class CaptioningL1 extends TestBase {

    private static String username = "";
    private static String password = "";
    private static List<String> sourceLanguages = new ArrayList<String>();
    private static List<String> targetLanguages = new ArrayList<String>();
    private static List<String> formattings = new ArrayList<String>();
    private static List<Double> unitCosts = new ArrayList<Double>();
    private static List<Double> transltranscCosts = new ArrayList<Double>();
    private static List<Double> timeCodes = new ArrayList<Double>();
    private static List<Double> length = new ArrayList<Double>();

    private double totalUnitCost = 0;
    private double grandtotal = 0;
    private double transcationCost = 0;
    private double orderCost = 0;
    private double transCost = 0;
    private double timeCode = 0;

    private String unitStatus = "";
    private String totalUnitStatus = "";
    private String grandtotalStatus = "";
    private String transcationStatus = "";
    private String orderStatus = "";
    private String transStatus = "";
    private String timeCodeStatus = "";

    @Test
    public void testStep() throws IOException, InterruptedException {


        sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 2; i++) {
            //driver.get(System.getProperty("website"));
            driver.get("https://vananservices.com/Captioning-Quote.php");

            CaptioningPOM captioningPOM = new CaptioningPOM(driver);

            System.out.print("S.No " + (i + 1));
            System.out.print("Source language : " + sourceLanguages.get(i));
            System.out.print("Target language : " + targetLanguages.get(i));
            System.out.print("Unit costs : " + unitCosts.get(i));
            System.out.print("Formatting : " + formattings.get(i));

            captioningPOM.selectSourceLanguage(sourceLanguages.get(i));
            captioningPOM.selectTargetLanguage(targetLanguages.get(i));
            captioningPOM.selectFormatting(formattings.get(i));
            if (length.get(i) <= 59) {

                captioningPOM.enterMinutes(length.get(i).intValue() + "");
            } else {
                int minutes = length.get(i).intValue() % 60;
                int hours = length.get(i).intValue() / 60;
                if (minutes != 0) {

                    captioningPOM.enterMinutes(minutes + "");
                }
                captioningPOM.enterHours(hours + "");
            }

            totalUnitCost = length.get(i) * unitCosts.get(i);
            if(sourceLanguages.get(i).equals("English")&&targetLanguages.get(i).equals("English")) {
                timeCode = 0;
            } else  {
                timeCode = timeCodes.get(i) * length.get(i).intValue();
            }
            transCost = length.get(i) * transltranscCosts.get(i);
            grandtotal = totalUnitCost + timeCode + transCost;
            transcationCost = grandtotal * 0.05;
            orderCost = grandtotal + transcationCost;

            unitStatus = checkStatus(unitCosts.get(i), captioningPOM.getBasePrice(), "Unit cost");
            totalUnitStatus = checkStatus(totalUnitCost, captioningPOM.getTotalUnitCost(), "Total Unit cost");
            if(sourceLanguages.get(i).equals("English")&&targetLanguages.get(i).equals("English")) {
                timeCodeStatus = "No Time code cost for English";
            } else {
                timeCodeStatus = checkStatus(timeCode, captioningPOM.getTimeCodePrice(), "Time Code cost");
            }
            transcationStatus = checkStatus(transCost, captioningPOM.getTranslationPrice(), "Transcription/Translation cost");
            grandtotalStatus = checkStatus(grandtotal, captioningPOM.getGrandTotal(), "Grand Total");
            transcationStatus = checkStatus(transcationCost, captioningPOM.getTransactionFee(), "Transaction fee");
            orderStatus = checkStatus(orderCost, captioningPOM.getOrderTotal(), "Order total");

            cell = sheet.getRow(i + 1).getCell(7);
            cell.setCellValue(totalUnitCost);
            cell = sheet.getRow(i + 1).getCell(8);
            cell.setCellValue(transCost);
            cell = sheet.getRow(i + 1).getCell(9);
            cell.setCellValue(timeCode);
            cell = sheet.getRow(i + 1).getCell(10);
            cell.setCellValue(grandtotal);
            cell = sheet.getRow(i + 1).getCell(11);
            cell.setCellValue(transcationCost);
            cell = sheet.getRow(i + 1).getCell(12);
            cell.setCellValue(orderCost);
            cell = sheet.getRow(i + 1).getCell(13);
            cell.setCellValue(unitStatus);
            cell = sheet.getRow(i + 1).getCell(14);
            cell.setCellValue(totalUnitStatus);
            cell = sheet.getRow(i + 1).getCell(15);
            cell.setCellValue(transStatus);
            cell = sheet.getRow(i + 1).getCell(16);
            cell.setCellValue(timeCodeStatus);
            cell = sheet.getRow(i + 1).getCell(17);
            cell.setCellValue(grandtotalStatus);
            cell = sheet.getRow(i + 1).getCell(18);
            cell.setCellValue(transcationStatus);
            cell = sheet.getRow(i + 1).getCell(19);
            cell.setCellValue(orderStatus);
            cell = sheet.getRow(i + 1).getCell(20);
            if(sourceLanguages.get(i).equals("English")&&targetLanguages.get(i).equals("English")) {
                if (unitStatus.equals("Pass") && totalUnitStatus.equals("Pass")
                        && transcationStatus.equals("Pass") && grandtotalStatus.equals("Pass")
                        && transcationStatus.equals("Pass") && orderStatus.equals("Pass")) {

                    cell.setCellValue("Pass");
                } else {
                    cell.setCellValue("Fail");
                }
            } else {
                if (unitStatus.equals("Pass") && totalUnitStatus.equals("Pass")&& timeCodeStatus.equals("Pass")
                        && transcationStatus.equals("Pass") && grandtotalStatus.equals("Pass")
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
        super.setCapabilities();
        readTranslateData();
        fileOutput = new FileOutputStream(file);
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }

    public static void readTranslateData() throws IOException {

        file = new File("src/test/resources/Captioning/CaptioningL1.xls");
        fileInput = new FileInputStream(file);
        workbook = new HSSFWorkbook(fileInput);
        sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            cell = sheet.getRow(i).getCell(0);
            sourceLanguages.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(1);
            targetLanguages.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(2);
            formattings.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(3);
            unitCosts.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(4);
            transltranscCosts.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(5);
            timeCodes.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(6);
            length.add(cell.getNumericCellValue());
        }
    }

}
