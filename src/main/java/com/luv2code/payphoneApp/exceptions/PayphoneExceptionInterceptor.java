package com.luv2code.payphoneApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class PayphoneExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PayphoneException.class)
    public final ResponseEntity<Object>  handleAllExceptions(PayphoneException ex){

        PayphoneExceptionSchema payphoneExceptionSchema = new PayphoneExceptionSchema(
                ex.getMessage(), ex.getDetails(), ex.getHint(),ex.getSupport());

        return  new ResponseEntity(payphoneExceptionSchema, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PayphoneIdException.class)
    public final ResponseEntity<Object> handlePhoneNumberExceptions(PayphoneIdException ex){
        PayphoneIdResponse payphoneIdResponse = new PayphoneIdResponse(ex.getMessage());

        return new ResponseEntity(payphoneIdResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public final ResponseEntity<Object> handleUsernameAlreadyExist(UsernameAlreadyExistException ex){

        UsernameAlreadyExistResponse response = new UsernameAlreadyExistResponse(ex.getMessage());
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

}
