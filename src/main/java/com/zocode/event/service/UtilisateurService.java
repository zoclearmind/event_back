package com.zocode.event.service;

import com.google.zxing.WriterException;
import com.zocode.event.dto.UtilisateurDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface UtilisateurService {
    UtilisateurDTO ajouterUtilisateur(UtilisateurDTO utilisateurDTO, String event_id) throws IOException, WriterException, MessagingException;
}