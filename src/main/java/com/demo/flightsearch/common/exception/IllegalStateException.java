package com.demo.flightsearch.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Illegal state exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalStateException extends RuntimeException {
    /**
     * Instantiates a new Illegal state exception.
     *
     * @param message the message
     */
    public IllegalStateException(String message) {
        super(message);
    }
}
