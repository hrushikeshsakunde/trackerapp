package com.service.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Dto class for adding event in session
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    @NotNull(message = "session id id cannot be null")
    private UUID sessionId;

    private List<EventDto> events;

}
