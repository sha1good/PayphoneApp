package com.luv2code.payphoneApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PayphoneIdException  extends RuntimeException{
    public PayphoneIdException(String message) {
        super(message);
    }
}
