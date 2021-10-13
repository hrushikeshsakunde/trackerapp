package com.service.tracker.dto;

import com.service.tracker.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto class for new session request
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String eventAt;
    private EventType eventType;
    private String payload;

}
