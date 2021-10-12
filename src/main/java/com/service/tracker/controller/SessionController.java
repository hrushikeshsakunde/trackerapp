package com.service.tracker.controller;


import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.dto.EndSessionRequest;
import com.service.tracker.dto.SessionResponse;
import com.service.tracker.services.SessionManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Handle session management for user
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

@RestController
@RequestMapping("/api/tracker")
public class SessionController {

    private final SessionManagementService sessionManagementService;

    public SessionController(SessionManagementService sessionManagementService) {
        this.sessionManagementService = sessionManagementService;
    }

    /**
     * Create session for tracker
     * @param createSessionRequest
     * @return sessionId in SessionResponse if successful
     */
    @PostMapping("/startSession")
    @ResponseStatus(HttpStatus.OK)
    public SessionResponse startSession(@Valid @RequestBody CreateSessionRequest createSessionRequest) {
        return sessionManagementService.createSession(createSessionRequest);
    }

    /**
     * End session of tracker
     * @param endSessionRequest
     */

    @PostMapping("/endSession")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void endSession(@Valid @RequestBody EndSessionRequest endSessionRequest) {
        sessionManagementService.endSession(endSessionRequest);
    }
}
