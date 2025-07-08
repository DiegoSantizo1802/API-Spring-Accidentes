package com.example.BackEnd.PG1.Service;

import com.example.BackEnd.PG1.Entity.Statistic;
import com.example.BackEnd.PG1.Repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    // Métodos CRUD básicos
    public List<Statistic> getAllStatistics() {
        return statisticRepository.findAll();
    }

    public Optional<Statistic> getStatisticById(Integer id) {
        return statisticRepository.findById(id);
    }

    public Statistic saveStatistic(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    public Optional<Statistic> updateStatistic(Integer id, Statistic statistic) {
        if (!statisticRepository.existsById(id)) {
            return Optional.empty();
        }
        statistic.setId(id);
        return Optional.of(statisticRepository.save(statistic));
    }

    public boolean deleteStatistic(Integer id) {
        if (!statisticRepository.existsById(id)) {
            return false;
        }
        statisticRepository.deleteById(id);
        return true;
    }

    // Métodos de búsqueda específicos
    public Optional<Statistic> findByZoneNumber(Integer zoneNumber) {
        return statisticRepository.findByZoneNumber(zoneNumber);
    }

    public Optional<Statistic> findByZoneName(String zoneName) {
        return statisticRepository.findByZoneName(zoneName);
    }

    public List<Statistic> findByAccidentType(String accidentType) {
        return statisticRepository.findByAccidentType(accidentType);
    }

    public List<Statistic> findByVehicleType(String vehicleType) {
        return statisticRepository.findByVehicleType(vehicleType);
    }

    public List<Statistic> findByDepartment(String department) {
        return statisticRepository.findByDepartment(department);
    }

    public List<Statistic> findByPeriod(String period) {
        return statisticRepository.findByPeriod(period);
    }
} 