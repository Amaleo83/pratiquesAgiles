Feature: Gestion des opérations

  Scenario: Nouvelle opération dans une salle libre
    Given Patient enregistré
      | nom    | prenom  | age |
      | COLLET | Quentin | 38  |
    And Personnel paramedical enregistré
      | nom     | prenom | age | metier    |
      | POIDRAS | Leo    | 24  | infirmier |
    When La salle d'opération suivante est libre
      | numeroSalle | horraire                      |
      | 1           | 2024-03-06T15:48:22.808873300 |
    Then Nouvelle operation enregistre

  Scenario: Nouvelle opération dans une salle non libre
    Given Patient enregistré
      | nom    | prenom  | age |
      | COLLET | Quentin | 38  |
    And Personnel paramedical enregistré
      | nom     | prenom | age | metier    |
      | POIDRAS | Leo    | 24  | infirmier |
    When La salle d'opération suivante n'est pas libre
      | numeroSalle | horraire                      |
      | 1           | 2024-03-06T15:48:22.808873300 |
    Then Nouvelle operation non enregistre