package com.service.tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.tracker.dto.CreateEventRequest;
import com.service.tracker.dto.CreateSessionRequest;
import com.service.tracker.dto.EventDto;
import com.service.tracker.dto.SessionResponse;
import com.service.tracker.enums.EventType;
import com.service.tracker.mapper.EventDtoToEventMapper;
import com.service.tracker.repository.EventRepository;
import com.service.tracker.repository.SessionRepository;
import com.service.tracker.services.EventManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventManagementService eventManagementService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDtoToEventMapper eventDtoToEventMapper;

    private final List<EventDto> events = new ArrayList<>();

    @BeforeEach
    private void setUp() {
        EventDto eventOne = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_ON, "test payload");
        EventDto eventTwo = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_OFF, "test payload");
        events.add(eventOne);
        events.add(eventTwo);
    }


    @Test
    public void testCreateSession() throws Exception {

        // Create session
        CreateSessionRequest createSessionRequest = new CreateSessionRequest(UUID.randomUUID(), UUID.randomUUID(), 1234, LocalDateTime.now().toString());

        MvcResult mvcCreateSession = mockMvc.perform(
                        post("/api/tracker/startSession")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createSessionRequest)))
                .andExpect(status().isOk()).andReturn();
        SessionResponse response = objectMapper.readValue(mvcCreateSession.getResponse().getContentAsString(), SessionResponse.class);
        Assertions.assertNotNull(response.getSessionID());

        //Prepare create event request
        CreateEventRequest createEventRequest = new CreateEventRequest(response.getSessionID(), events);

        // add events
        mockMvc.perform(
                        post("/api/tracker/addEvents")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createEventRequest)))
                .andExpect(status().isCreated());
    }


    @Test
    public void testAddEvent_Throw_NotFoundException() throws Exception {

        CreateEventRequest createEventRequest = new CreateEventRequest(UUID.randomUUID(), events);

        mockMvc.perform(
                        post("/api/tracker/addEvents")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createEventRequest)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testAddEvent_Throw_BadRequestException() throws Exception {

        mockMvc.perform(
                        post("/api/tracker/addEvents")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString("{}")))
                .andExpect(status().isBadRequest());

    }


}
