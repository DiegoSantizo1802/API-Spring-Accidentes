package com.example.BackEnd.PG1.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accident_table")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "occurrence_year")
    private Integer yearOccurrence;

    @Column(name = "hour_group")
    private String hourGroup;

    @Column(name = "month")
    private Integer month;

    @Column(name = "day")
    private Integer day;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "department")
    private String department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statistic_id")
    @JsonBackReference
    private Statistic statistic;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "event_type")
    private String eventType;
}
