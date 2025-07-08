package com.example.BackEnd.PG1.Service;

import com.example.BackEnd.PG1.Entity.Accident;
import com.example.BackEnd.PG1.Repository.AccidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> getAccidentById(Long id) {
        return accidentRepository.findById(id);
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Optional<Accident> updateAccident(Long id, Accident accident) {
        if (!accidentRepository.existsById(id)) {
            return Optional.empty();
        }
        accident.setId(id);
        return Optional.of(accidentRepository.save(accident));
    }

    public boolean deleteAccident(Long id) {
        if (!accidentRepository.existsById(id)) {
            return false;
        }
        accidentRepository.deleteById(id);
        return true;
    }
} 