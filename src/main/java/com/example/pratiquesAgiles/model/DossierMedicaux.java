package com.example.pratiquesAgiles.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DossierMedicaux {
    private String nomDossier;
    private RendezVousMedecin rendezVousMedecin;
    private String compteRendu;
}
