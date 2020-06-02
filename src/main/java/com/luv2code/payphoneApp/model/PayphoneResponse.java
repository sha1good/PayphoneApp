package com.luv2code.payphoneApp.model;

public class PayphoneResponse {


    private  Paymentmethods[] paymentmethods;



    public PayphoneResponse(){

    }

    public Paymentmethods[] getPaymentmethods() {
        return paymentmethods;
    }

    public void setPaymentmethods(Paymentmethods[] paymentmethods) {
        this.paymentmethods = paymentmethods;
    }


}
