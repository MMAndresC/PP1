package com.svalero.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Gardener not found")
public class GardenerNotFoundException extends RuntimeException {

    public GardenerNotFoundException(String message){
        super(message);
    }

}
