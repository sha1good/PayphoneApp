package com.luv2code.payphoneApp.exceptions;

public class PayphoneIdResponse {

    private String phoneNumber;

    public PayphoneIdResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
