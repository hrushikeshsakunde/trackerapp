package com.service.tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.tracker.mapper.CreateSessionRequestToSessionMapper;
import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.dto.EndSessionRequest;
import com.service.tracker.dto.SessionResponse;
import com.service.tracker.repository.SessionRepository;
import com.service.tracker.services.SessionManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class sessionControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CreateSessionRequestToSessionMapper createSessionRequestToSessionMapper;

    @Test
    public void testCreateSession() throws Exception {

        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, LocalDateTime.now().toString());

        MvcResult mvcCreateSession = mockMvc.perform(
                        post("/api/tracker/startSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createSessionRequest)))
                .andExpect(status().isOk()).andReturn();
        SessionResponse response = objectMapper.readValue(mvcCreateSession.getResponse().getContentAsString(), SessionResponse.class);
        Assertions.assertNotNull(response.getSessionID());
    }


    @Test
    public void testCreateSession_ThrowExceptionTest() throws Exception {

        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, "teststamp");

        mockMvc.perform(
                        post("/api/tracker/startSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createSessionRequest)))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void testEndSession_ThrowNotFoundExceptionTest() throws Exception {

        EndSessionRequest endSessionRequest = new EndSessionRequest(UUID.randomUUID(), LocalDateTime.now().toString());

        mockMvc.perform(
                        post("/api/tracker/endSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(endSessionRequest)))
                .andExpect(status().isNotFound());

    }


    @Test
    public void testEndSessionTest() throws Exception {

        // Create session
        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, LocalDateTime.now().toString());

        MvcResult mvcCreateSession = mockMvc.perform(
                        post("/api/tracker/startSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createSessionRequest)))
                .andExpect(status().isOk()).andReturn();
        SessionResponse response = objectMapper.readValue(mvcCreateSession.getResponse().getContentAsString(), SessionResponse.class);
        Assertions.assertNotNull(response.getSessionID());


        //end session
        EndSessionRequest endSessionRequest = new EndSessionRequest(response.getSessionID(), LocalDateTime.now().toString());

        mockMvc.perform(
                        post("/api/tracker/endSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(endSessionRequest)))
                .andExpect(status().isAccepted());

    }



}
