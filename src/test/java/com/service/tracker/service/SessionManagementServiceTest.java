package com.service.tracker.service;

import com.service.tracker.Mapper.CreateSessionRequestToSessionMapper;
import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.dto.EndSessionRequest;
import com.service.tracker.dto.SessionResponse;
import com.service.tracker.model.Session;
import com.service.tracker.repository.SessionRepository;
import com.service.tracker.services.SessionManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionManagementServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private CreateSessionRequestToSessionMapper createSessionRequestToSessionMapper;

    @InjectMocks
    private SessionManagementService tested;


    @Test
    void testCreateSessionTest() {

        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, LocalDateTime.now().toString());
        Session session = new Session();
        session.setUserId(createSessionRequest.getUserId());
        session.setMachineId(createSessionRequest.getMachineId());

        when(createSessionRequestToSessionMapper.from(createSessionRequest)).thenReturn(session);
        when(sessionRepository.findSessionByUserIdAndMachineIdAndEndAtIsNull(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());

        SessionResponse result = tested.createSession(createSessionRequest);
        verify(sessionRepository, times(1)).save(any(Session.class));

    }

    @Test
    void testEndSessionTest() {

        EndSessionRequest endSessionRequest = new EndSessionRequest(UUID.randomUUID(), LocalDateTime.now().toString());
        when(sessionRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Session()));

        tested.endSession(endSessionRequest);

        verify(sessionRepository, times(1)).save(any(Session.class));

    }

}
