package com.example.projectproto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ExcelController {

    @Autowired
    private ExcelReaderService excelReaderService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public String checkApiStatus() {
        return "API is running";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/upload-excel")
    public ResponseEntity<String> handleExcelUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Provide the actual path to save the file
                String excelFilePath = "C:\\Users\\workhorse\\Documents" + file.getOriginalFilename();

                // Save the uploaded Excel file
                file.transferTo(new File(excelFilePath));

                // Read and save data to MongoDB
                excelReaderService.readAndSaveExcel(excelFilePath);

                // Delete the uploaded file after processing
                new File(excelFilePath).delete();

                return ResponseEntity.status(HttpStatus.OK).body("Upload successful");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uploaded file is empty");
        }
    }
}
