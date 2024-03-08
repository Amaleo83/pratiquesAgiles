Feature: Gestion des médecins

  Scenario: Nouveau médecin arrive à l'hôpital
    Given Nouveau medecin
      | nom     | prenom | age |
      | POIDRAS | Leo    | 24  |
    When Le medecin n'est pas enregistré
    Then On enregistre le medecin