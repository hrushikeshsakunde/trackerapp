package com.service.tracker.services;

import com.service.tracker.mapper.CreateSessionRequestToSessionMapper;
import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.dto.EndSessionRequest;
import com.service.tracker.dto.SessionResponse;
import com.service.tracker.exception.ResourceNotFoundException;
import com.service.tracker.model.Session;
import com.service.tracker.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * This class contain business logic for session management
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

@Service
public class SessionManagementService {

    private final SessionRepository sessionRepository;
    private final CreateSessionRequestToSessionMapper createSessionRequestToSessionMapper;

    public SessionManagementService(SessionRepository sessionRepository, CreateSessionRequestToSessionMapper createSessionRequestToSessionMapper) {
        this.sessionRepository = sessionRepository;
        this.createSessionRequestToSessionMapper = createSessionRequestToSessionMapper;
    }

    /**
     *  Crete session for user
     * @param createSessionRequest
     * @return SessionResponse
     */
    public SessionResponse createSession(CreateSessionRequest createSessionRequest){

        // Covert session request to session object
        Session session = createSessionRequestToSessionMapper.from(createSessionRequest);

        //Check if user already have open session by userid and machineId
        Optional<Session> existingSession = sessionRepository.findSessionByUserIdAndMachineIdAndEndAtIsNull(session.getUserId(), session.getMachineId());

        if(existingSession.isPresent()){
            return new SessionResponse(existingSession.get().getId());
        }

        // save session object
        sessionRepository.save(session);

        return new SessionResponse(session.getId());
    }


    /**
     * End session by session id
     * @param endSessionRequest
     */
    public void endSession(EndSessionRequest endSessionRequest) {

        //Check for existing session
        Session existingSession = sessionRepository.findById(endSessionRequest.getSessionId()).orElseThrow(
                () -> new ResourceNotFoundException("Session not found")
        );

        LocalDateTime endAt = LocalDateTime.parse(endSessionRequest.getEndAt());

        // add end at to session and save session
        existingSession.setEndAt(endAt);
        sessionRepository.save(existingSession);
    }
}
