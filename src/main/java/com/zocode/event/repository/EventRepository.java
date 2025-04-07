package com.zocode.event.repository;

import com.zocode.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findByPriorityAndIsDeletedFalse(Integer priority);
    Optional<Event> findByPriorityAndIsDeletedFalseAndActiveTrue(Integer priority);
}
