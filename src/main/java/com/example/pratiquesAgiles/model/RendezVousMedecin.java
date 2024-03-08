package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RendezVousMedecin {
    private LocalDateTime dateRdv;

    private Medecin medecin;

    private Patient patient;
}
