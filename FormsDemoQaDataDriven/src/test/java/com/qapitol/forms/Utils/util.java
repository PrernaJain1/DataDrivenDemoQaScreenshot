package com.qapitol.forms.Utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class util {

    //To read file
    public static Object[][] getExcelData(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook =new XSSFWorkbook(fis);
//        workbook.getSheet("");   //To get a sheet by its name
        XSSFSheet sheet =workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row =sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object[][] data = new Object[rowCount-1][colCount];

        for(int i=0;i<rowCount-1; i++){
            row = sheet.getRow(i+1);
            for(int j=0;j<colCount;j++){
                XSSFCell cell = row.getCell(j);
                data[i][j]= dataFormatter.formatCellValue(cell);
            }
        }
        return data;
    }
}
