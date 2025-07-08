package com.example.BackEnd.PG1.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics_table")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "zone_number", unique = true)
    private Integer zoneNumber;

    @Column(name = "zone_name", unique = true)
    private String zoneName;

    @Column(name = "zone_classification")
    private String zoneClassification;

    @Column(name = "accident_type")
    private String accidentType;

    private Integer frequency;

    @Column(name = "common_hour")
    private String commonHour;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "department")
    private String department;

    @Column(name = "period")
    private String period;

    @OneToMany(mappedBy = "statistic", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Accident> accidents;
}