package com.example;

import com.example.pratiquesAgiles.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.example")
public class CucumberTest {

    protected final ListAll listAll = new ListAll();
    protected final Patient patient = new Patient(listAll);
    protected final Medecin medecin = new Medecin(listAll);
    protected final SalleOperation salleOperation = new SalleOperation();
    protected final RendezVousOperation rendezVousOperation = new RendezVousOperation();
    protected final PersonnelParamedical personnelParamedical = new PersonnelParamedical();

    @Given("Nouveau rendez-vous le {}")
    public void nouveauRdv(String date) {
        patient.addRendezVous(patient, medecin, LocalDateTime.parse(date));
        medecin.addRendezVous(patient, medecin, LocalDateTime.parse(date));
        assertFalse(patient.getRendezVousMedecinList().isEmpty());
        assertFalse(medecin.getRendezVousMedecinList().isEmpty());
    }

    @When("Dossier enregistrer avec le compte rendu suivant {}")
    public void dossierEnregistrerAvecLeCompteRenduSuivant(String compteRendu) {
        DossierMedicaux dossierMedicaux = new DossierMedicaux();
        dossierMedicaux.setCompteRendu(compteRendu);
        dossierMedicaux.setRendezVousMedecin(patient.getRendezVousMedecinList().get(0));
        listAll.getDossierMedicaux().add(dossierMedicaux);
    }

    @Then("Modification du compte rendu pour {}")
    public void modificationDuCompteRendu(String compteRendu) {
        DossierMedicaux dossierMedicaux = listAll.getDossierMedicaux().get(0);
        dossierMedicaux.setCompteRendu(compteRendu);
        assertEquals(compteRendu, dossierMedicaux.getCompteRendu());
    }
}
