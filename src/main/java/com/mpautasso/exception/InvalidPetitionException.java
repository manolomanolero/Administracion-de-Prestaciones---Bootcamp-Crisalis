package com.mpautasso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPetitionException extends RuntimeException{
    public InvalidPetitionException(String mensaje){
        super(mensaje);
    }
}
