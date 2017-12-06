package com.cgilbers.xyzairlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class represents a not found exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No such object")  // 404
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(){

    }

    public ObjectNotFoundException(String message){
        super(message);
    }
}
