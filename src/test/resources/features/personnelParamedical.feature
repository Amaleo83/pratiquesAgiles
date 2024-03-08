Feature: Gestion des personnel paramedicaux

  Scenario: Nouveau infirmier arrive à l'hôpital
    Given Nouveau personnel paramedical
      | nom     | prenom | age | metier    |
      | POIDRAS | Leo    | 24  | infirmier |
    When Le personnel paramedical n'est pas enregistré
    Then On enregistre le personnel paramedical