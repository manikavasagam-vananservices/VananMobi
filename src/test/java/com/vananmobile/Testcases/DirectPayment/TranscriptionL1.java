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

import com.vananmobile.PageObjects.TranscriptionPOM;

public class TranscriptionL1 extends TestBase {

    private static String username = "";
    private static String password = "";

    private String service = "Translation";

    private static List<String> language = new ArrayList<String>();
    private static List<String> purpose = new ArrayList<String>();
    private static List<Double> length= new ArrayList<Double>();
    private static List<Double> unitCost = new ArrayList<Double>();
    private static List<Double> timeCodeCost = new ArrayList<Double>();

    private double totalUnitCost = 0;
    private double grandtotal = 0;
    private double transcationCost = 0;
    private double orderCost = 0;
    private double timeCode = 0;

    private String unitStatus = "";
    private String totalUnitStatus = "";
    private String grandtotalStatus = "";
    private String transcationStatus = "";
    private String orderStatus = "";
    private String timeCodeStatus = "";

    @Test
    public void testStep() throws IOException, InterruptedException {


        sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 2; i++) {
            //driver.get(System.getProperty("website"));
            driver.get("https://vananservices.com/Transcription-Quote.php");

            TranscriptionPOM transcriptionPOM = new TranscriptionPOM(driver);

            System.out.print("S.No " + (i + 1));
            System.out.print("Source language : " + language.get(i));
            System.out.print("Purpose : " + purpose.get(i));
            System.out.print("Length : " + length.get(i));
            System.out.print("Unit costs : " + unitCost.get(i));

            if(purpose.get(i).equals("Personal")) {
                transcriptionPOM.selectPersonal();
            } else if(purpose.get(i).equals("Business")) {
                transcriptionPOM.selectBusiness();
            }
            if(length.get(i)<=59) {

                transcriptionPOM.enterMinutes(length.get(i).intValue()+"");
            } else {
                int minutes = length.get(i).intValue() % 60;
                int hours = length.get(i).intValue() / 60;
                if(minutes!=0) {

                    transcriptionPOM.enterMinutes(minutes +"");
                }
                transcriptionPOM.enterHours(hours+"");
            }

            transcriptionPOM.selectLanguageFrom(language.get(i));
            transcriptionPOM.selectCategory("General");
            transcriptionPOM.selectTimeCode("Every 3 sec");
            transcriptionPOM.selectSpeakerCount("1 speaker");
            transcriptionPOM.clickNextButton();
            Thread.sleep(2000);
            transcriptionPOM.clickNextButton();
            Thread.sleep(2000);
            if (unitCost.get(i).intValue()!=0) {

                totalUnitCost = length.get(i) * unitCost.get(i);
                timeCode = timeCodeCost.get(i) * length.get(i).intValue();
                grandtotal = totalUnitCost + timeCode;
                transcationCost = grandtotal * 0.05;
                orderCost = grandtotal + transcationCost;

                unitStatus = checkStatus(unitCost.get(i), transcriptionPOM.getBasePrice(), "Unit cost");
                totalUnitStatus = checkStatus(totalUnitCost, transcriptionPOM.getUnitCost(), "Total Unit cost");
                timeCodeStatus = checkStatus(timeCode, transcriptionPOM.getTimeCode(), "Time Code cost");
                grandtotalStatus = checkStatus(grandtotal, transcriptionPOM.getTotalCost(), "Grand Total");
                transcationStatus = checkStatus(transcationCost, transcriptionPOM.getTranscationFee(), "Transaction fee");
                orderStatus = checkStatus(orderCost, transcriptionPOM.getGrandTotal(), "Order total");

                cell = sheet.getRow(i + 1).getCell(5);
                cell.setCellValue(totalUnitCost);
                cell = sheet.getRow(i + 1).getCell(6);
                cell.setCellValue(timeCode);
                cell = sheet.getRow(i + 1).getCell(7);
                cell.setCellValue(grandtotal);
                cell = sheet.getRow(i + 1).getCell(8);
                cell.setCellValue(transcationCost);
                cell = sheet.getRow(i + 1).getCell(9);
                cell.setCellValue(orderCost);
                cell = sheet.getRow(i + 1).getCell(10);
                cell.setCellValue(unitStatus);
                cell = sheet.getRow(i + 1).getCell(11);
                cell.setCellValue(totalUnitStatus);
                cell = sheet.getRow(i + 1).getCell(12);
                cell.setCellValue(timeCodeStatus);
                cell = sheet.getRow(i + 1).getCell(13);
                cell.setCellValue(grandtotalStatus);
                cell = sheet.getRow(i + 1).getCell(14);
                cell.setCellValue(transcationStatus);
                cell = sheet.getRow(i + 1).getCell(15);
                cell.setCellValue(orderStatus);
                cell = sheet.getRow(i + 1).getCell(16);

                if (unitStatus.equals("Pass") && totalUnitStatus.equals("Pass") && timeCodeStatus.equals("Pass")
                        && grandtotalStatus.equals("Pass") && transcationStatus.equals("Pass") && orderStatus.equals("Pass")) {

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

        file = new File("src/test/resources/Transcription/TranscriptionL1.xls");
        fileInput = new FileInputStream(file);
        workbook = new HSSFWorkbook(fileInput);
        sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            cell = sheet.getRow(i).getCell(0);
            language.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(1);
            purpose.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(2);
            length.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(3);
            unitCost.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(4);
            timeCodeCost.add(cell.getNumericCellValue());
        }
    }
}
