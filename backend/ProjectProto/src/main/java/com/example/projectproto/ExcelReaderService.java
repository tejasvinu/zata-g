package com.example.projectproto;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelReaderService {

    @Autowired
    private MongoRepo dataRepository;

    public void readAndSaveExcel(String excelFilePath) {
        try (FileInputStream inputStream = new FileInputStream(excelFilePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            // Assuming the first row contains headers, adjust if needed
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Product data = new Product();

                // Map each cell to the corresponding field in your data model
                data.setProductId(getStringCellValue(row.getCell(0)));
                data.setProductName(getStringCellValue(row.getCell(1)));
                // Add other fields as needed

                try {
                    dataRepository.save(data);
                } catch (Exception e) {
                    // Log the exception or throw a custom exception with a meaningful message
                    System.err.println("Error saving data to MongoDB: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // Log the exception or throw a custom exception with a meaningful message
            System.err.println("Error reading Excel file: " + e.getMessage());
        }
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Handle numeric values as needed
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                // Handle boolean values as needed
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
}
