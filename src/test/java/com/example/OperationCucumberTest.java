package com.example;

import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OperationCucumberTest extends CucumberTest {

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
}
