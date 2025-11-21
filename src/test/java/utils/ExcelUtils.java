package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * Read test data from Excel file
     * @param filePath Path to Excel file
     * @param sheetName Sheet name
     * @return 2D Object array for DataProvider
     */
    public static Object[][] getTestData(String filePath, String sheetName) {
        Object[][] data = null;
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                LogUtils.error("Sheet '" + sheetName + "' not found in Excel file");
                return new Object[0][0];
            }
            
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            
            data = new Object[rowCount][colCount];
            
            // Start from row 1 (skip header row 0)
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        data[i - 1][j] = getCellValue(cell);
                    }
                }
            }
            
            LogUtils.info("Successfully read " + rowCount + " rows from Excel: " + filePath);
            
        } catch (IOException e) {
            LogUtils.error("Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        }
        
        return data;
    }

    /**
     * Get cell value as String
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Get data from specific row
     */
    public static Object[] getRowData(String filePath, String sheetName, int rowNumber) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNumber);
            
            if (row == null) {
                return new Object[0];
            }
            
            int colCount = row.getLastCellNum();
            Object[] rowData = new Object[colCount];
            
            for (int i = 0; i < colCount; i++) {
                rowData[i] = getCellValue(row.getCell(i));
            }
            
            return rowData;
            
        } catch (IOException e) {
            LogUtils.error("Error reading Excel row: " + e.getMessage());
            e.printStackTrace();
            return new Object[0];
        }
    }
}
