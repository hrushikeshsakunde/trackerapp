package com.service.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
public class CreateSessionRequest {

    @NotNull(message = "User id cannot be null")
    private UUID userId;

    @NotNull(message = "Machine id cannot be null")
    private UUID machineId;

    @NotNull(message = "Origin id cannot be null")
    private Integer orgId;

    private String startAt;

}
