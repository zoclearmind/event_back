package com.zocode.event.dto;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UtilisateurDTO {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String entreprise;
    private String secteurActivite;
    private String poste;
    private String region;
    private String profilLinkedIn;
    private Set<String> centresInteret;
    private Boolean recevoirMisesAJour;
    private String qrCodeId;

}


