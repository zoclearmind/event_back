package com.zocode.event.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCreateDTO {
    private String title;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String description;
    private String location;
}
