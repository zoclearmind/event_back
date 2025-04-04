package com.zocode.event.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventDTO {
    private UUID id;
    private String title;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String description;
    private String location;
    private String imageUrl;
}
