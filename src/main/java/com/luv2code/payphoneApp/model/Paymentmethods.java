package com.luv2code.payphoneApp.model;

public class Paymentmethods {

     private String paymentMethodTypeCode;
    private String  paymentMethodTypeName;
    private String  paymentMethodCode ;
    private String   paymentMethodName;
    private String   paymentMethodPAN;
    private String   paymentMethodStatus;
    private String   paymentMethodExpiryDate;

     private String paymentMethodIssuerCbnCode;
      private String paymentMethodIdentifier;
       private String   paymentMethodAccountNumber;


    public Paymentmethods(){

    }

    public String getPaymentMethodIdentifier() {
        return paymentMethodIdentifier;
    }

    public void setPaymentMethodIdentifier(String paymentMethodIdentifier) {
        this.paymentMethodIdentifier = paymentMethodIdentifier;
    }

    public String getPaymentMethodTypeCode() {
        return paymentMethodTypeCode;
    }

    public void setPaymentMethodTypeCode(String paymentMethodTypeCode) {
        this.paymentMethodTypeCode = paymentMethodTypeCode;
    }

    public String getPaymentMethodTypeName() {
        return paymentMethodTypeName;
    }

    public void setPaymentMethodTypeName(String paymentMethodTypeName) {
        this.paymentMethodTypeName = paymentMethodTypeName;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodPAN() {
        return paymentMethodPAN;
    }

    public void setPaymentMethodPAN(String paymentMethodPAN) {
        this.paymentMethodPAN = paymentMethodPAN;
    }

    public String getPaymentMethodStatus() {
        return paymentMethodStatus;
    }

    public void setPaymentMethodStatus(String paymentMethodStatus) {
        this.paymentMethodStatus = paymentMethodStatus;
    }

    public String getPaymentMethodExpiryDate() {
        return paymentMethodExpiryDate;
    }

    public void setPaymentMethodExpiryDate(String paymentMethodExpiryDate) {
        this.paymentMethodExpiryDate = paymentMethodExpiryDate;
    }

    public String getPaymentMethodIssuerCbnCode() {
        return paymentMethodIssuerCbnCode;
    }

    public void setPaymentMethodIssuerCbnCode(String paymentMethodIssuerCbnCode) {
        this.paymentMethodIssuerCbnCode = paymentMethodIssuerCbnCode;
    }

    public String getPaymentMethodAccountNumber() {
        return paymentMethodAccountNumber;
    }

    public void setPaymentMethodAccountNumber(String paymentMethodAccountNumber) {
        this.paymentMethodAccountNumber = paymentMethodAccountNumber;
    }


}
