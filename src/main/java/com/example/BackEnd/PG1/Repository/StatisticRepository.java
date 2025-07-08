package com.example.BackEnd.PG1.Repository;

import com.example.BackEnd.PG1.Entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
    
    Optional<Statistic> findByZoneNumber(Integer zoneNumber);
    
    Optional<Statistic> findByZoneName(String zoneName);
    
    List<Statistic> findByAccidentType(String accidentType);
    
    List<Statistic> findByVehicleType(String vehicleType);
    
    List<Statistic> findByDepartment(String department);
    
    List<Statistic> findByPeriod(String period);
} 