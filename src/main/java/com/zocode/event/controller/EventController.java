package com.zocode.event.controller;

import com.zocode.event.dto.EventCreateDTO;
import com.zocode.event.dto.EventDTO;
import com.zocode.event.mapper.EventMapper;
import com.zocode.event.model.Event;
import com.zocode.event.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<EventDTO> createEvent(
            @RequestPart("data") EventCreateDTO eventCreateDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        Event savedEvent = eventService.createEvent(eventCreateDTO, image);
        EventDTO eventDTO = eventMapper.toDTO(savedEvent);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/couverture")
    public ResponseEntity<EventDTO> getEventConverture() {
        Event event = eventService.getEventConverture()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Événement de couverture introuvable"));
        EventDTO eventDTO = eventMapper.toDTO(event);
        return ResponseEntity.ok(eventDTO);
    }

}
