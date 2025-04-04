package com.zocode.event.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAuthenticationEmail(String to, String firstName, String lastName, byte[] qrCode) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Votre Badge pour l'Événement");
        helper.setText("<p>Bonjour " + firstName + " " + lastName + ",</p>" +
                "<p>Vous êtes inscrit(e) à l'événement <strong>FIM 2025</strong>.</p>" +
                "<p>Veuillez trouver ci-joint votre <strong>badge de visiteur</strong> en PDF.</p>" +
                "<p>Vous pouvez l'imprimer dès maintenant ou le faire imprimer à l'entrée de l'événement.</p>" +
                "<p>Merci et à bientôt !</p>", true);

        // Générer le PDF avec le badge
        byte[] pdfBytes = generateBadgePdf(firstName, lastName, qrCode);

        // Ajouter le PDF en pièce jointe
        InputStreamSource pdfSource = new ByteArrayResource(pdfBytes);
        helper.addAttachment("Badge.pdf", pdfSource);

        // Envoyer l'email
        mailSender.send(message);
    }

    private byte[] generateBadgePdf(String firstName, String lastName, byte[] qrCode) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Badge - ÉVÉNEMENT : FIM 2025").setBold().setFontSize(16));
        document.add(new Paragraph("Nom : " + lastName));
        document.add(new Paragraph("Prénom : " + firstName));

        // Ajouter l'image QR Code
        ImageData imageData = ImageDataFactory.create(qrCode);
        Image qrCodeImage = new Image(imageData);
        qrCodeImage.setWidth(150);
        qrCodeImage.setHeight(150);
        document.add(qrCodeImage);

        document.close();
        return outputStream.toByteArray();
    }
}
