package com.cgilbers.xyzairlines.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class represents an incorrect destination exception
 * @author cgilbers
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Plane is already at the destination")
public class IncorrectDestinationException extends RuntimeException{

}
