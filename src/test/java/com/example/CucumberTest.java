package com.example;

import com.example.pratiquesAgiles.model.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.example")
public class CucumberTest {

    public final ListAll listAll = new ListAll();
    public final Patient patient = new Patient(listAll);
    public final Medecin medecin = new Medecin(listAll);
    public final SalleOperation salleOperation = new SalleOperation();
    public final RendezVousOperation rendezVousOperation = new RendezVousOperation();
    public final PersonnelParamedical personnelParamedical = new PersonnelParamedical();

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

    @When("La salle d'opération suivante n'est pas libre")
    public void salleOperationNEstPasLibreEnregistre(DataTable table) {
        setSalleOperation(table, false);
        listAll.getSalleOperations().add(salleOperation);
    }

    @When("La salle d'opération suivante est libre")
    public void salleOperationLibreEnregistre(DataTable table) {
        setSalleOperation(table, true);
        listAll.getSalleOperations().add(salleOperation);
    }

    private void setSalleOperation(DataTable table, boolean libre) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            int numeroSalle = Integer.parseInt(row.get("numeroSalle"));
            LocalDateTime horraire = LocalDateTime.parse(row.get("horraire"));

            salleOperation.setNumeroSalle(numeroSalle);
            salleOperation.setHorraire(horraire);
            salleOperation.setLibre(libre);
        }
    }

    @Given("Nouveau medecin")
    public void nouveauMedecin(DataTable table) {
        setMedecin(table);
    }


    @Given("Medecin enregistré")
    public void medecinEnregistre(DataTable table) {
        setMedecin(table);
        listAll.getListMedecins().add(medecin);
    }

    @When("Le Patient n'est pas enregistré")
    public void patientNonEnregistre() {
        assertFalse(listAll.getListPatients().contains(patient));
    }

    @When("Le medecin n'est pas enregistré")
    public void medecinNonEnregistre() {
        assertFalse(listAll.getListMedecins().contains(medecin));
    }

    @Then("On enregistre le medecin")
    public void onEnregistreLeMedecin() {
        listAll.getListMedecins().add(medecin);
        assertTrue(listAll.getListMedecins().contains(medecin));
    }

    @When("Le Medecin est bien enregistré")
    public void leMedecinEstBienEnregistre() {
        assertTrue(listAll.getListPatients().contains(patient));
    }

    private void setMedecin(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String nom = row.get("nom");
            String prenom = row.get("prenom");
            int age = Integer.parseInt(row.get("age"));

            medecin.setNom(nom);
            medecin.setPrenom(prenom);
            medecin.setAge(age);
        }
    }

    @Then("Nouvelle operation non enregistre")
    public void nouvelleOperationNonEnregistre() {
        setRendezVousOperation();

        assertEquals(patient, rendezVousOperation.getPatient());
        assertNull(rendezVousOperation.getSalleOperation());
        assertEquals(personnelParamedical, rendezVousOperation.getPersonnelParamedicalList().get(0));
    }

    @Then("Nouvelle operation enregistre")
    public void nouvelleOperationEnregistre() {
        setRendezVousOperation();

        assertEquals(patient, rendezVousOperation.getPatient());
        assertEquals(salleOperation, rendezVousOperation.getSalleOperation());
        assertEquals(personnelParamedical, rendezVousOperation.getPersonnelParamedicalList().get(0));
    }

    private void setRendezVousOperation() {
        if (salleOperation.isLibre()) {
            rendezVousOperation.setSalleOperation(salleOperation);
        }
        rendezVousOperation.setPatient(patient);
        rendezVousOperation.setPersonnelParamedicalList(List.of(personnelParamedical));
    }

    @Given("Nouveau patient")
    public void nouveauPatient(DataTable table) {
        setPatient(table);
    }

    @Then("On enregistre le patient")
    public void onEnregistreLePatient() {
        listAll.getListPatients().add(patient);
        assertTrue(listAll.getListPatients().contains(patient));
    }

    @When("Le Patient est bien enregistré")
    public void lePatientEstBienEnregistre() {
        assertTrue(listAll.getListPatients().contains(patient));
    }

    @Given("Patient enregistré")
    public void patientEnregistre(DataTable table) {
        setPatient(table);
        listAll.getListPatients().add(patient);
    }

    private void setPatient(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String nom = row.get("nom");
            String prenom = row.get("prenom");
            int age = Integer.parseInt(row.get("age"));

            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setAge(age);
        }
    }


    @Given("Personnel paramedical enregistré")
    public void personnelParamedicalEnregistre(DataTable table) {
        setPersonnelParamedical(table);
        listAll.getPersonnelParamedicalList().add(personnelParamedical);
    }

    @Given("Nouveau personnel paramedical")
    public void nouveauPersonnelParamedical(DataTable table) {
        setPersonnelParamedical(table);
    }

    @When("Le personnel paramedical n'est pas enregistré")
    public void personnelParamedicalNonEnregistre() {
        assertFalse(listAll.getPersonnelParamedicalList().contains(personnelParamedical));
    }

    @Then("On enregistre le personnel paramedical")
    public void onEnregistreLePersonnelParamedical() {
        listAll.getPersonnelParamedicalList().add(personnelParamedical);
        assertTrue(listAll.getPersonnelParamedicalList().contains(personnelParamedical));
    }


    private void setPersonnelParamedical(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String nom = row.get("nom");
            String prenom = row.get("prenom");
            String metier = row.get("metier");
            int age = Integer.parseInt(row.get("age"));

            personnelParamedical.setNom(nom);
            personnelParamedical.setPrenom(prenom);
            personnelParamedical.setAge(age);
            personnelParamedical.setMetier(metier);
        }
    }

}