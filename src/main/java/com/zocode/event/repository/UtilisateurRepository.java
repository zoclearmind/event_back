package com.zocode.event.repository;

import com.zocode.event.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
    Optional<Utilisateur> findByEmail(String email);

    boolean existsByQrCodeId(String qrCodeId);
}
