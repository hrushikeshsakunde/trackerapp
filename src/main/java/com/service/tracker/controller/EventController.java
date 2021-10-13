package com.service.tracker.controller;


import com.service.tracker.dto.CreateEventRequest;
import com.service.tracker.services.EventManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Handle Event management for session
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

@RestController
@RequestMapping("/api/tracker")
public class EventController {

    private final EventManagementService eventManagementService;

    public EventController(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }


    /**
     * Add events for session
     *
     * @param createEventRequest
     * @return sessionId in SessionResponse if successful
     */
    @PostMapping("/addEvents")
    @ResponseStatus(HttpStatus.CREATED)
    public void startSession(@Valid @RequestBody CreateEventRequest createEventRequest) {
        eventManagementService.addEventToSession(createEventRequest);
    }


}
