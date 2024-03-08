Feature: Dossier Medicaux

  Scenario: Modifier dossier patient
    Given Patient enregistré
      | nom    | prenom  | age |
      | COLLET | Quentin | 38  |
    And Medecin enregistré
      | nom     | prenom | age |
      | POIDRAS | Leo    | 24  |

    When Le Patient est bien enregistré
    And Le Medecin est bien enregistré
    And Nouveau rendez-vous le 2024-03-06T15:48:22.808873300
    And Dossier enregistrer avec le compte rendu suivant angine
    Then Modification du compte rendu pour scoliose
