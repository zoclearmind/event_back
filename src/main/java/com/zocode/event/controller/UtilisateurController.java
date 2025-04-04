package com.zocode.event.controller;

import com.google.zxing.WriterException;
import com.zocode.event.dto.UtilisateurDTO;
import com.zocode.event.service.UtilisateurService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<UtilisateurDTO> ajouterUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateurDTO,@PathVariable String event_id) throws MessagingException, IOException, WriterException {
        UtilisateurDTO utilisateurCree = utilisateurService.ajouterUtilisateur(utilisateurDTO,event_id);
        return ResponseEntity.ok(utilisateurCree);
    }

}

