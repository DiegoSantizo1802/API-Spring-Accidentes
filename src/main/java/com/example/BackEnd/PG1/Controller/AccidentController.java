package com.example.BackEnd.PG1.Controller;

import com.example.BackEnd.PG1.Entity.Accident;
import com.example.BackEnd.PG1.Service.AccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accidents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping
    public ResponseEntity<List<Accident>> getAllAccidents() {
        return ResponseEntity.ok(accidentService.getAllAccidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accident> getAccidentById(@PathVariable Long id) {
        return accidentService.getAccidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Accident> createAccident(@RequestBody Accident accident) {
        return ResponseEntity.ok(accidentService.saveAccident(accident));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accident> updateAccident(@PathVariable Long id, @RequestBody Accident accident) {
        return accidentService.updateAccident(id, accident)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccident(@PathVariable Long id) {
        if (accidentService.deleteAccident(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
} 