package com.example.BackEnd.PG1.Controller;

import com.example.BackEnd.PG1.Entity.Statistic;
import com.example.BackEnd.PG1.Service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class StatisticController {

    private final StatisticService statisticService;

    // Endpoints CRUD básicos
    @GetMapping
    public ResponseEntity<List<Statistic>> getAllStatistics() {
        return ResponseEntity.ok(statisticService.getAllStatistics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statistic> getStatisticById(@PathVariable Integer id) {
        return statisticService.getStatisticById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Statistic> createStatistic(@RequestBody Statistic statistic) {
        return ResponseEntity.ok(statisticService.saveStatistic(statistic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statistic> updateStatistic(@PathVariable Integer id, @RequestBody Statistic statistic) {
        return statisticService.updateStatistic(id, statistic)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable Integer id) {
        if (statisticService.deleteStatistic(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoints específicos para zonas
    @GetMapping("/zone/{zoneNumber}")
    public ResponseEntity<Statistic> getStatisticByZoneNumber(@PathVariable Integer zoneNumber) {
        return statisticService.findByZoneNumber(zoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/zone/name/{zoneName}")
    public ResponseEntity<Statistic> getStatisticByZoneName(@PathVariable String zoneName) {
        return statisticService.findByZoneName(zoneName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoints para búsquedas específicas
    @GetMapping("/type/{accidentType}")
    public ResponseEntity<List<Statistic>> getStatisticsByAccidentType(@PathVariable String accidentType) {
        return ResponseEntity.ok(statisticService.findByAccidentType(accidentType));
    }

    @GetMapping("/vehicle/{vehicleType}")
    public ResponseEntity<List<Statistic>> getStatisticsByVehicleType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(statisticService.findByVehicleType(vehicleType));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Statistic>> getStatisticsByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(statisticService.findByDepartment(department));
    }

    @GetMapping("/period/{period}")
    public ResponseEntity<List<Statistic>> getStatisticsByPeriod(@PathVariable String period) {
        return ResponseEntity.ok(statisticService.findByPeriod(period));
    }
} 