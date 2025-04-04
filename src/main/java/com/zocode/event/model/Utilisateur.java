package com.zocode.event.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Numéro de téléphone invalide")
    private String telephone;

    @NotBlank(message = "Le nom de l'entreprise est obligatoire")
    private String entreprise;

    @NotBlank(message = "Le secteur d’activité est obligatoire")
    private String secteurActivite;

    @NotBlank(message = "Le poste / fonction est obligatoire")
    private String poste;

    @NotBlank(message = "La région / ville est obligatoire")
    private String region;

    private String profilLinkedIn;

    @ElementCollection
    private Set<String> centresInteret;

    @NotBlank(message = "QrCodeId est obligatoire")
    @Column(unique = true)
    private String qrCodeId;

    private Boolean recevoirMisesAJour;

    // 🔥 Correction de la relation avec Event
    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
}
