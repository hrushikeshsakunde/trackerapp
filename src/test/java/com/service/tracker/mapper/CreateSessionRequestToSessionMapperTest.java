package com.service.tracker.mapper;

import com.service.tracker.Mapper.CreateSessionRequestToSessionMapper;
import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.model.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;


public class CreateSessionRequestToSessionMapperTest {

    private CreateSessionRequestToSessionMapper tested = new CreateSessionRequestToSessionMapper();



    @Test
    void testCreateSessionRequestToSessionMapper() {

        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, LocalDateTime.now().toString());

        Session result = tested.from(createSessionRequest);

        Assertions.assertEquals(createSessionRequest.getUserId(), result.getUserId());
        Assertions.assertEquals(createSessionRequest.getMachineId(), result.getMachineId());
        Assertions.assertEquals(createSessionRequest.getOrgId(), result.getOrgId());
        Assertions.assertEquals(createSessionRequest.getStartAt(), result.getStartAt().toString());

    }


}
