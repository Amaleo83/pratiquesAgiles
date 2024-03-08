package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SalleOperation {
    private int numeroSalle;
    private LocalDateTime horraire;
    private boolean libre;
}
