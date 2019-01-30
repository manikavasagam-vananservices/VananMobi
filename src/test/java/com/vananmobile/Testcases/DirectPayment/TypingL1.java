package com.vananmobile.Testcases.DirectPayment;

import com.vananmobile.Common.TestBase;
import com.vananmobile.PageObjects.TypingPOM;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypingL1 extends TestBase {

    private static String username = "";
    private static String password = "";

    private static List<String> languages = new ArrayList<String>();
    private static List<String> fileTypes = new ArrayList<String>();
    private static List<String> categorys = new ArrayList<String>();
    private static List<String> formattings = new ArrayList<String>();
    private static List<Double> pages = new ArrayList<Double>();
    private static List<Double> unitCosts = new ArrayList<Double>();

    private double totalUnitCost = 0;
    private double grandtotal = 0;
    private double transcationCost = 0;
    private double orderCost = 0;

    private String unitStatus = "";
    private String totalUnitStatus = "";
    private String grandtotalStatus = "";
    private String transcationStatus = "";
    private String orderStatus = "";

    @Test
    public void testStep() throws IOException, InterruptedException {


        sheet = workbook.getSheetAt(0);

        for (int i = 0; i < 2; i++) {
            //driver.get(System.getProperty("website"));
            driver.get("https://vananservices.com/Typing-Quote.php");

            TypingPOM typingPOM = new TypingPOM(driver);

            System.out.print("S.No " + (i + 1));
            System.out.print("Source language : " + languages.get(i));
            System.out.print("File Type : " + fileTypes.get(i));
            System.out.print("Categorys : " + categorys.get(i));
            System.out.print("Formatting : " + formattings.get(i));
            System.out.print("Pages : " + pages.get(i));
            System.out.print("Unit costs : " + unitCosts.get(i));

            typingPOM.selectFileType(fileTypes.get(i));
            typingPOM.enterPageCount(pages.get(i)+"");
            typingPOM.selectLanguageFrom(languages.get(i));
            typingPOM.selectCategory(categorys.get(i));
            typingPOM.selectFormatting(formattings.get(i));

            totalUnitCost = pages.get(i) * unitCosts.get(i);
            grandtotal = totalUnitCost;
            transcationCost = grandtotal * 0.05;
            orderCost = grandtotal + transcationCost;

            unitStatus = checkStatus(unitCosts.get(i), typingPOM.getBasePrice(), "Unit cost");
            totalUnitStatus = checkStatus(totalUnitCost, typingPOM.getUnitCost(), "Total Unit cost");
            grandtotalStatus = checkStatus(grandtotal, typingPOM.getGrandTotal(), "Grand Total");
            transcationStatus = checkStatus(transcationCost, typingPOM.getTransactionFee(), "Transaction fee");
            orderStatus = checkStatus(orderCost, typingPOM.getOrderTotal(), "Order total");

            cell = sheet.getRow(i + 1).getCell(6);
            cell.setCellValue(totalUnitCost);
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
            cell.setCellValue(grandtotalStatus);
            cell = sheet.getRow(i + 1).getCell(13);
            cell.setCellValue(transcationStatus);
            cell = sheet.getRow(i + 1).getCell(14);
            cell.setCellValue(orderStatus);
            cell = sheet.getRow(i + 1).getCell(15);

            if (unitStatus.equals("Pass") && totalUnitStatus.equals("Pass")
                    && grandtotalStatus.equals("Pass") && transcationStatus.equals("Pass") && orderStatus.equals("Pass")) {

                cell.setCellValue("Pass");
            } else {
                cell.setCellValue("Fail");
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

        file = new File("src/test/resources/Typing/TypingL1.xls");
        fileInput = new FileInputStream(file);
        workbook = new HSSFWorkbook(fileInput);
        sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            cell = sheet.getRow(i).getCell(0);
            languages.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(1);
            fileTypes.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(2);
            categorys.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(3);
            formattings.add(cell.getStringCellValue());
            cell = sheet.getRow(i).getCell(4);
            pages.add(cell.getNumericCellValue());
            cell = sheet.getRow(i).getCell(5);
            unitCosts.add(cell.getNumericCellValue());
        }
    }
}
