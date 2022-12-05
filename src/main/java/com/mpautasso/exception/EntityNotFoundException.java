package com.mpautasso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends PersistenceException {
    public EntityNotFoundException(String mensaje){
        super(mensaje);
    }
}
