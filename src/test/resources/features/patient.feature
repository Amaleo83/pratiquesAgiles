Feature: Processus patient

  Scenario: Nouveau patient arrive à l'hôpital
    Given Nouveau patient
      | nom    | prenom  | age |
      | COLLET | Quentin | 38  |
    When Le Patient n'est pas enregistré
    Then On enregistre le patient

  Scenario: Patient connu arrive à l'hôpital
    Given Patient enregistré
      | nom    | prenom  | age |
      | COLLET | Quentin | 38  |
    And Medecin enregistré
      | nom     | prenom | age |
      | POIDRAS | Leo    | 24  |
    When Le Patient est bien enregistré
    And Le Medecin est bien enregistré
    And Nouveau rendez-vous le 2024-03-06T15:48:22.808873300