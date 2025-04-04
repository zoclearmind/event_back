package com.zocode.event.repository;

import com.zocode.event.model.EventUtilisateurLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventUtilisateurLoginRepository extends JpaRepository<EventUtilisateurLogin, Long> {
}
