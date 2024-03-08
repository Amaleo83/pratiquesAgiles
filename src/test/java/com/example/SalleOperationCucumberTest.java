package com.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SalleOperationCucumberTest extends CucumberTest {

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
}
