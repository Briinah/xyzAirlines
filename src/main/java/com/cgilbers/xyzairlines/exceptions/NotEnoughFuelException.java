package com.cgilbers.xyzairlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class represents a not enough fuel exception
 * @author cgilbers
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Not enough fuel")
public class NotEnoughFuelException extends RuntimeException {

    public NotEnoughFuelException(){}

    public NotEnoughFuelException(String message){
        super(message);
    }
}
