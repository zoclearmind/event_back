package com.zocode.event.service;

import com.zocode.event.dto.EventCreateDTO;
import com.zocode.event.dto.EventDTO;
import com.zocode.event.model.Event;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    Event createEvent(EventCreateDTO eventCreateDTO, MultipartFile image) throws IOException;

    List<EventDTO> getAllEvents();
}
