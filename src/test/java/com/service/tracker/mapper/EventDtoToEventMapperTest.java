package com.service.tracker.mapper;

import com.service.tracker.dto.EventDto;
import com.service.tracker.enums.EventType;
import com.service.tracker.model.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventDtoToEventMapperTest {

    private final EventDtoToEventMapper tested = new EventDtoToEventMapper();


    @Test
    void testEventDtoToEventMapper() {

        EventDto eventOne = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_ON, "test payload");
        EventDto eventTwo = new EventDto(LocalDateTime.now().toString(), EventType.MACHINE_OFF, "test payload");

        List<Event> result = tested.from(List.of(eventOne, eventTwo));

        assertEquals(2, result.size());

        assertEquals(eventOne.getEventAt(), result.get(0).getEventAt().toString());
        assertEquals(eventOne.getEventType(), result.get(0).getEventType());
        assertEquals(eventOne.getPayload(), result.get(0).getPayload());

        assertEquals(eventTwo.getEventAt(), result.get(1).getEventAt().toString());
        assertEquals(eventTwo.getEventType(), result.get(1).getEventType());
        assertEquals(eventTwo.getPayload(), result.get(1).getPayload());

    }


}
