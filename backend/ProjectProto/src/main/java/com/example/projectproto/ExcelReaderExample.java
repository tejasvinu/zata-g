package com.example.projectproto;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReaderExample {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\workhorse\\Documents\\ProjectProto\\Proto1.xlsx";

        try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Assuming the first sheet is of interest, adjust if needed
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                // Iterate over each cell in the row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Print the cell value
                    System.out.print(cellToString(cell) + "\t");
                }

                System.out.println(); // Move to the next line for the next row
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String cellToString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
