package com.olegb.service.Controller;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message ,Integer id){
        super("Error 404: Could not find "+message+" " + id);
    }
}
