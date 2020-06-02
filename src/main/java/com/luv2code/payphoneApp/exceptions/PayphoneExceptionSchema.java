package com.luv2code.payphoneApp.exceptions;

public class PayphoneExceptionSchema {

    private String  message;
    private String details;
    private String hint;
    private  String support;

    public PayphoneExceptionSchema(){

    }

    public PayphoneExceptionSchema(String message, String details, String hint, String support) {
        this.message = message;
        this.details = details;
        this.hint = hint;
        this.support = support;
    }

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
