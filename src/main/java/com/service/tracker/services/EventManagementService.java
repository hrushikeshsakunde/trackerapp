package com.service.tracker.services;

import com.service.tracker.dto.CreateEventRequest;
import com.service.tracker.exception.ResourceNotFoundException;
import com.service.tracker.mapper.EventDtoToEventMapper;
import com.service.tracker.model.Event;
import com.service.tracker.model.Session;
import com.service.tracker.repository.EventRepository;
import com.service.tracker.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * This class contain business logic for event management
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

@Service
public class EventManagementService {

    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;
    private final EventDtoToEventMapper eventDtoToEventMapper;

    public EventManagementService(EventRepository eventRepository, SessionRepository sessionRepository, EventDtoToEventMapper eventDtoToEventMapper) {
        this.eventRepository = eventRepository;
        this.sessionRepository = sessionRepository;
        this.eventDtoToEventMapper = eventDtoToEventMapper;
    }


    /**
     * Crete session for user
     *
     * @param createEventRequest
     *
     */
    public void addEventToSession(CreateEventRequest createEventRequest) {


        //Check for valid session
        Session session = sessionRepository.findById(createEventRequest.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        // convert event dto to event object
        List<Event> events = eventDtoToEventMapper.from(createEventRequest.getEvents());


        // save events
        if (!CollectionUtils.isEmpty(events)) {
            events.forEach(e -> e.setSession(session));
            eventRepository.saveAll(events);
        }


    }


}
