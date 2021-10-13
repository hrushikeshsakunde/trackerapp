package com.service.tracker.service;

import com.service.tracker.dto.CreateEventRequest;
import com.service.tracker.dto.EventDto;
import com.service.tracker.enums.EventType;
import com.service.tracker.exception.ResourceNotFoundException;
import com.service.tracker.mapper.EventDtoToEventMapper;
import com.service.tracker.model.Event;
import com.service.tracker.model.Session;
import com.service.tracker.repository.EventRepository;
import com.service.tracker.repository.SessionRepository;
import com.service.tracker.services.EventManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventManagementServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventDtoToEventMapper eventDtoToEventMapper;

    @InjectMocks
    private EventManagementService tested;


    @Test
    void testAddEvents() {

        EventDto eventOne = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_ON, "test payload");

        CreateEventRequest createEventRequest = new CreateEventRequest(UUID.randomUUID(), List.of(eventOne));

        Event event = new Event();

        when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Session()));
        when(eventDtoToEventMapper.from(List.of(eventOne))).thenReturn(List.of(event));

        tested.addEventToSession(createEventRequest);

        verify(eventRepository, times(1)).saveAll(any());

    }

    @Test
    void testAddEventsWithInValidSessionId() {

        EventDto eventOne = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_ON, "test payload");
        CreateEventRequest createEventRequest = new CreateEventRequest(UUID.randomUUID(), List.of(eventOne));
        when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> tested.addEventToSession(createEventRequest));
    }


}
