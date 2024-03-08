package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RendezVousOperation {
    private Patient patient;
    private SalleOperation salleOperation;
    private List<PersonnelParamedical> personnelParamedicalList;
}
