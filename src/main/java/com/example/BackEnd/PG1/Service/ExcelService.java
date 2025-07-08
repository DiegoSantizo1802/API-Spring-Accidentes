package com.example.BackEnd.PG1.Service;

import com.example.BackEnd.PG1.Entity.Accident;
import com.example.BackEnd.PG1.Entity.Statistic;
import com.example.BackEnd.PG1.Repository.AccidentRepository;
import com.example.BackEnd.PG1.Repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final AccidentRepository accidentRepository;
    private final StatisticRepository statisticRepository;

    private static final Map<String, Integer> MESES = new HashMap<String, Integer>() {{
        put("ENERO", 1);
        put("FEBRERO", 2);
        put("MARZO", 3);
        put("ABRIL", 4);
        put("MAYO", 5);
        put("JUNIO", 6);
        put("JULIO", 7);
        put("AGOSTO", 8);
        put("SEPTIEMBRE", 9);
        put("OCTUBRE", 10);
        put("NOVIEMBRE", 11);
        put("DICIEMBRE", 12);
    }};

    private static final Map<String, Integer> DIAS = new HashMap<String, Integer>() {{
        put("LUNES", 1);
        put("MARTES", 2);
        put("MIÉRCOLES", 3);
        put("MIERCOLES", 3);
        put("JUEVES", 4);
        put("VIERNES", 5);
        put("SÁBADO", 6);
        put("SABADO", 6);
        put("DOMINGO", 7);
    }};

    public List<Accident> importExcelFile(MultipartFile file) throws IOException {
        List<Accident> accidents = new ArrayList<>();
        
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Saltamos la fila de encabezados
            if (rows.hasNext()) {
                rows.next();
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                
                // Obtener o crear la estadística para la zona
                Integer zoneNumber = getIntegerValue(currentRow.getCell(8));
                Statistic statistic = getOrCreateStatistic(zoneNumber);

                String mesTexto = getStringValue(currentRow.getCell(2)).toUpperCase().trim();
                String diaTexto = getStringValue(currentRow.getCell(3)).toUpperCase().trim();

                Accident accident = Accident.builder()
                    .yearOccurrence(getIntegerValue(currentRow.getCell(0)))
                    .hourGroup(getStringValue(currentRow.getCell(1)))
                    .month(MESES.getOrDefault(mesTexto, 0))  // Convertimos el texto del mes a número
                    .day(DIAS.getOrDefault(diaTexto, 0))     // Convertimos el texto del día a número
                    .municipality(getStringValue(currentRow.getCell(4)))
                    .department(getStringValue(currentRow.getCell(5)))
                    .vehicleType(getStringValue(currentRow.getCell(6)))
                    .eventType(getStringValue(currentRow.getCell(7)))
                    .statistic(statistic)
                    .build();

                accidents.add(accident);
            }
        }

        return accidentRepository.saveAll(accidents);
    }

    private Statistic getOrCreateStatistic(Integer zoneNumber) {
        Optional<Statistic> existingStatistic = statisticRepository.findByZoneNumber(zoneNumber);
        
        if (existingStatistic.isPresent()) {
            return existingStatistic.get();
        }

        // Crear una estadística básica solo con el número de zona
        Statistic newStatistic = Statistic.builder()
            .zoneNumber(zoneNumber)
            .zoneName("Zona " + zoneNumber) // Nombre temporal
            .build();

        return statisticRepository.save(newStatistic);
    }

    private String getStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private Integer getIntegerValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                try {
                    return Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return 0;
                }
            default:
                return 0;
        }
    }
} 