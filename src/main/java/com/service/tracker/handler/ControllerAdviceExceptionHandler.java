package com.service.tracker.handler;

import com.service.tracker.dto.ApiResponse;
import com.service.tracker.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

/**
 * Transfers all service exception into an unsuccessfull ApiResponse. Later on, we might add more infos
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */
@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);


    /**
     * @param ex
     * @return APiResponse
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiResponse> handleBadRequests(DateTimeParseException ex) {
        log.info("Handled DateTimeParseException", ex);
        return new ResponseEntity<>(new ApiResponse(false, "Invalid Date"), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex
     * @return APiResponse
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("Handled ResourceNotFoundException", ex);
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex
     * @return APiResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAnyOtherException(Exception ex) {
        if (log.isErrorEnabled()) {
            log.error(String.format("Handled unexpected exception of type: %s", ex.getClass().getName()), ex);
        }

        return new ResponseEntity<>(new ApiResponse(false, "Unable to process your requests"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
