package com.service.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Standard Api response in case of exception
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private boolean success;

    private String message;

}
