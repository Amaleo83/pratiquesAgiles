package com.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonnelMedicalCucumberTest extends CucumberTest {

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
