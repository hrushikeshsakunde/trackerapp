package com.service.tracker.mapper;

import com.service.tracker.dto.EventDto;
import com.service.tracker.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mapper class to map event dto object to event
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Component
public class EventDtoToEventMapper implements Mapper<EventDto, Event> {

    @Override
    public Event from(EventDto eventDto) {
        Event event = new Event();
        event.setEventType(eventDto.getEventType());
        event.setPayload(eventDto.getPayload());
        LocalDateTime eventAt = LocalDateTime.parse(eventDto.getEventAt());
        event.setEventAt(eventAt);
        return event;
    }
}
