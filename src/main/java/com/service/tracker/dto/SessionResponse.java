package com.service.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Dto class for session creation response
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponse {
    private UUID sessionID;
}
