package com.zocode.event.mapper;

import com.zocode.event.dto.EventDTO;
import com.zocode.event.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO toDTO(Event event) {
        if (event == null) return null;

        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDateDebut(event.getDateDebut());
        dto.setDateFin(event.getDateFin());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());
        dto.setImageUrl(event.getImageUrl());
        return dto;
    }

    public Event toEntity(EventDTO dto) {
        if (dto == null) return null;

        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setDateDebut(dto.getDateDebut());
        event.setDateFin(dto.getDateFin());
        event.setLocation(dto.getLocation());
        event.setDescription(dto.getDescription());
        event.setImageUrl(dto.getImageUrl());
        return event;
    }
}

