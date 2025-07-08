package com.example.BackEnd.PG1.Repository;

import com.example.BackEnd.PG1.Entity.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {
    
    @Query("SELECT a.department as categoria, COUNT(a) as cantidad " +
           "FROM Accident a GROUP BY a.department")
    List<Object[]> countByDepartment();
    
    @Query("SELECT a.vehicleType as categoria, COUNT(a) as cantidad " +
           "FROM Accident a GROUP BY a.vehicleType")
    List<Object[]> countByVehicleType();
    
    @Query("SELECT a.eventType as categoria, COUNT(a) as cantidad " +
           "FROM Accident a GROUP BY a.eventType")
    List<Object[]> countByEventType();
    
    @Query("SELECT CONCAT(a.month, '/', a.yearOccurrence) as periodo, COUNT(a) as cantidad " +
           "FROM Accident a GROUP BY a.month, a.yearOccurrence ORDER BY a.yearOccurrence, a.month")
    List<Object[]> countByMonthAndYear();

    @Query("SELECT CONCAT('Zona ', s.zoneNumber) as categoria, COUNT(a) as cantidad " +
           "FROM Accident a JOIN a.statistic s GROUP BY s.zoneNumber")
    List<Object[]> countByZone();
} 