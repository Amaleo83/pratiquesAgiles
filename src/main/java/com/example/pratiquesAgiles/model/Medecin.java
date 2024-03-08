package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Medecin {
    private String nom;
    private String prenom;
    private int age;

    private final ListAll listAll;

    private List<RendezVousMedecin> rendezVousMedecinList = new ArrayList<>();

    public Medecin(ListAll listAll) {
        this.listAll = listAll;
    }
    public void addRendezVous(Patient patient, Medecin medecin, LocalDateTime date) {
        RendezVousMedecin rendezVousMedecin = new RendezVousMedecin();
        rendezVousMedecin.setPatient(patient);
        rendezVousMedecin.setMedecin(medecin);
        rendezVousMedecin.setDateRdv(date);
        rendezVousMedecinList.add(rendezVousMedecin);
    }

}
