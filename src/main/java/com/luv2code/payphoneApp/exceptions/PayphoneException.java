package com.luv2code.payphoneApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PayphoneException  extends RuntimeException{

    private String  message;
    private String details;
    private String hint;
    private  String support;

    public PayphoneException(){

    }

   public  PayphoneException(String message, String details, String hint,  String support){
        this.message = message;
        this.details = details;
        this.hint = hint;
        this.support = support;


   }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
