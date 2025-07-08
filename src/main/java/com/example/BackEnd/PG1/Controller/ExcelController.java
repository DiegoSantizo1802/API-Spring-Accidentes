package com.example.BackEnd.PG1.Controller;

import com.example.BackEnd.PG1.Entity.Accident;
import com.example.BackEnd.PG1.Service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!isExcelFile(file)) {
                return ResponseEntity.badRequest().body("Por favor, sube un archivo Excel válido (xlsx)");
            }

            List<Accident> importedAccidents = excelService.importExcelFile(file);
            return ResponseEntity.ok(importedAccidents);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    private boolean isExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (
            contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || // .xlsx
            contentType.equals("application/vnd.ms-excel") // .xls
        );
    }
} 