package com.zocode.event.service.impl;

import com.google.zxing.WriterException;
import com.zocode.event.dto.UtilisateurDTO;
import com.zocode.event.mapper.UtilisateurMapper;
import com.zocode.event.model.Event;
import com.zocode.event.model.EventUtilisateurLogin;
import com.zocode.event.model.Utilisateur;
import com.zocode.event.repository.EventRepository;
import com.zocode.event.repository.EventUtilisateurLoginRepository;
import com.zocode.event.repository.UtilisateurRepository;
import com.zocode.event.service.EmailService;
import com.zocode.event.service.QRCodeService;
import com.zocode.event.service.UtilisateurService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final QRCodeService qrCodeService;
    private final EmailService emailService;
    private final EventUtilisateurLoginRepository eventUtilisateurLoginRepository;
    private final EventRepository eventRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  QRCodeService qrCodeService,
                                  EmailService emailService,
                                  EventUtilisateurLoginRepository eventUtilisateurLoginRepository,
                                  EventRepository eventRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
        this.eventUtilisateurLoginRepository = eventUtilisateurLoginRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public UtilisateurDTO ajouterUtilisateur(UtilisateurDTO utilisateurDTO, String event_id)
            throws IOException, WriterException, MessagingException {

        // Convertir String en UUID
        UUID eventUUID;
        try {
            eventUUID = UUID.fromString(event_id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + event_id);
        }

        Event event = eventRepository.findById(eventUUID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with ID: " + event_id));

        Utilisateur utilisateur = utilisateurRepository.findByEmail(utilisateurDTO.getEmail()).orElse(new Utilisateur());

        String qrCodeId;
        do {
            qrCodeId = String.format("%06d", new Random().nextInt(999999));
        } while (utilisateurRepository.existsByQrCodeId(qrCodeId));

        byte[] qrCode = qrCodeService.generateQRCode(qrCodeId, 300, 300);

        mettreAJourUtilisateur(utilisateur, utilisateurDTO, qrCodeId);

        Utilisateur saveUtilisateur = utilisateurRepository.save(utilisateur);

        EventUtilisateurLogin eventUtilisateurLogin = new EventUtilisateurLogin();
        eventUtilisateurLogin.setUtilisateur(saveUtilisateur);
        eventUtilisateurLogin.setEvent(event);

        eventUtilisateurLoginRepository.save(eventUtilisateurLogin);

        //envoyer email
        emailService.sendAuthenticationEmail(utilisateurDTO.getEmail(), utilisateurDTO.getNom(), utilisateurDTO.getPrenom(), qrCode);

        return utilisateurDTO;
    }

    // Méthode pour mettre à jour les champs utilisateur
    private void mettreAJourUtilisateur(Utilisateur utilisateur, UtilisateurDTO utilisateurDTO, String qrCodeId) {
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setTelephone(utilisateurDTO.getTelephone());
        utilisateur.setEntreprise(utilisateurDTO.getEntreprise());
        utilisateur.setSecteurActivite(utilisateurDTO.getSecteurActivite());
        utilisateur.setPoste(utilisateurDTO.getPoste());
        utilisateur.setRegion(utilisateurDTO.getRegion());
        utilisateur.setProfilLinkedIn(utilisateurDTO.getProfilLinkedIn());
        utilisateur.setCentresInteret(utilisateurDTO.getCentresInteret());
        utilisateur.setRecevoirMisesAJour(utilisateurDTO.getRecevoirMisesAJour());
        utilisateur.setQrCodeId(qrCodeId);
    }
}
