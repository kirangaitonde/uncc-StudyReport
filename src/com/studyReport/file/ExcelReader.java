package com.studyReport.file;

/**
 * class to read data from an excel file.
 *
 * @author K G 
 * Reference: 
 * https://poi.apache.org/,
 * https://docs.oracle.com/javase/8/docs/
 * 
 */
import com.studyReport.db.DBUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelReader {

    public static void readExcelInsertToDB() {
        try {
            DataFormatter fmt = new DataFormatter();
            FileInputStream file = new FileInputStream("data.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 1; i < totalRows; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] record = new String[totalCols];
                for (int j = 0; j < totalCols; j++) {
                    HSSFCell cell = row.getCell(j);
                    String value = fmt.formatCellValue(cell);
                    if(j==3||j==4||j==10||j==12){
                        if(value==null||value.equals("null")||value.equals("")){
                            value="0";
                        }
                    }
                    record[j]=value;
                }
               DBUtil.insertRecord(record[0], record[1],record[2] , Double.parseDouble(record[3]), Double.parseDouble(record[4]), record[5], record[6], record[7], record[8], record[9], record[10], record[11], Double.parseDouble(record[12]));
            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ExcelReader.readExcelInsertToDB();
    }

}
