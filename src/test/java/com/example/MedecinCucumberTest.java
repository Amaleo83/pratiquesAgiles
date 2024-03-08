package com.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedecinCucumberTest extends CucumberTest {
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
}
