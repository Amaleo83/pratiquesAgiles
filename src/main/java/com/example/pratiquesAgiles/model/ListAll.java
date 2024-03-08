package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ListAll {
    List<Patient> listPatients = new ArrayList<>();
    List<Medecin> listMedecins = new ArrayList<>();
    List<DossierMedicaux> dossierMedicaux = new ArrayList<>();
    List<SalleOperation> salleOperations = new ArrayList<>();
    List<PersonnelParamedical> personnelParamedicalList = new ArrayList<>();
}
