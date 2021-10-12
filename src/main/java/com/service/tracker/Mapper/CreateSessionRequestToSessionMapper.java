package com.service.tracker.Mapper;

import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.model.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mapper class to map CreateSessionRequest to Session object
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Component
public class CreateSessionRequestToSessionMapper implements Mapper<CreateSessionRequest, Session> {

    @Override
    public Session from(CreateSessionRequest createSessionRequest) {
        Session session = new Session();
        session.setUserId(createSessionRequest.getUserId());
        session.setMachineId(createSessionRequest.getMachineId());
        session.setOrgId(createSessionRequest.getOrgId());
        LocalDateTime startAt = LocalDateTime.parse(createSessionRequest.getStartAt());
        session.setStartAt(startAt);
        return session;
    }
}
