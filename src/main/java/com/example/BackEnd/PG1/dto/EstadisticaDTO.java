package com.example.BackEnd.PG1.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaDTO {
    private String periodo;
    private Long cantidad;
    private String categoria;
    private String tipoDato; 
} 