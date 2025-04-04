package com.zocode.event.service.impl;

import com.zocode.event.dto.EventCreateDTO;
import com.zocode.event.dto.EventDTO;
import com.zocode.event.mapper.EventMapper;
import com.zocode.event.model.Event;
import com.zocode.event.repository.EventRepository;
import com.zocode.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // Répertoire où les images seront enregistrées localement
    private final String UPLOAD_DIR = "uploads/";

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Event createEvent(EventCreateDTO eventCreateDTO, MultipartFile image) throws IOException {
        Event event = new Event();
        event.setTitle(eventCreateDTO.getTitle());
        event.setDateDebut(eventCreateDTO.getDateDebut());
        event.setDateFin(eventCreateDTO.getDateFin());
        event.setDescription(eventCreateDTO.getDescription());
        event.setLocation(eventCreateDTO.getLocation());
        Event savedEvent = eventRepository.save(event);

        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image, savedEvent.getId().toString());
            savedEvent.setImageUrl(imageUrl);
            eventRepository.save(savedEvent);
        }
        return savedEvent;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }


    private String saveImage(MultipartFile image, String eventId) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String originalFilename = image.getOriginalFilename();
        if (originalFilename != null) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String imageName = eventId + fileExtension;
            Path filePath = Paths.get(UPLOAD_DIR, imageName);
            Files.copy(image.getInputStream(), filePath);
            return "/uploads/" + imageName;
        } else {
            throw new IOException("Le fichier n'a pas de nom valide");
        }
    }
}
