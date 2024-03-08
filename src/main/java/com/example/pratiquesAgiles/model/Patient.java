package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Patient {
    private String nom;
    private String prenom;
    private int age;
    private List<RendezVousMedecin> rendezVousMedecinList = new ArrayList<>();

    private final ListAll listAll;

    public Patient(ListAll listAll) {
        this.listAll = listAll;
    }

    private void addPatient(Patient patient) {
        listAll.getListPatients().add(patient);
    }

    public void addRendezVous(Patient patient, Medecin medecin, LocalDateTime date) {
        RendezVousMedecin rendezVousMedecin = new RendezVousMedecin();
        rendezVousMedecin.setPatient(patient);
        rendezVousMedecin.setMedecin(medecin);
        rendezVousMedecin.setDateRdv(date);
        rendezVousMedecinList.add(rendezVousMedecin);
    }
}
