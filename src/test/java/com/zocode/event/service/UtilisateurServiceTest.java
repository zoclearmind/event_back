package com.zocode.event.service;

import com.zocode.event.dto.UtilisateurDTO;
import com.zocode.event.mapper.UtilisateurMapper;
import com.zocode.event.model.Utilisateur;
import com.zocode.event.repository.UtilisateurRepository;
import com.zocode.event.service.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceTest {
/*
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    private Utilisateur utilisateur;
    private UtilisateurDTO utilisateurDTO;

    @BeforeEach
    void setUp() {
        utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(UUID.randomUUID());
        utilisateurDTO.setPrenom("John");
        utilisateurDTO.setNom("Doe");
        utilisateurDTO.setEmail("john.doe@example.com");

        utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
    }

    @Test
    void testAjouterUtilisateur() {
        when(utilisateurMapper.toEntity(utilisateurDTO)).thenReturn(utilisateur);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        when(utilisateurMapper.toDTO(utilisateur)).thenReturn(utilisateurDTO);

        UtilisateurDTO result = utilisateurService.ajouterUtilisateur(utilisateurDTO);

        assertNotNull(result);
        assertEquals(utilisateurDTO.getEmail(), result.getEmail());
        assertEquals(utilisateurDTO.getPrenom(), result.getPrenom());

        verify(utilisateurRepository, times(1)).save(utilisateur);
    }*/
}

