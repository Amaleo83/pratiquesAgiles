package com.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientCucumberTest extends CucumberTest {
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
}
