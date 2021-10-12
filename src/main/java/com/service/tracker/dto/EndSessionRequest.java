package com.service.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * Dto class for end session request
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndSessionRequest {

    @NotNull(message = "Session id cannot be null")
    private UUID sessionId;

    private String endAt;

}
