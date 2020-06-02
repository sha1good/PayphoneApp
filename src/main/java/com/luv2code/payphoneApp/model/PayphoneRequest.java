package com.luv2code.payphoneApp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PayphoneRequest {

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    @NotBlank(message = "FirstName is required")
    private  String firstName;
    @NotBlank(message = "LastName is required")
    private   String lastName ;

    @Email(message = "Must be an Email Address")
    @NotBlank(message = "Email is Required")
     private String email;

    @NotBlank(message = "Terminal Transaction Id  is required")
    @Size(min = 8,max = 10, message = "Please use 8 to 10 Digits")
     private  String ttid;
    @NotBlank(message = "Card Pan is Required")
    @Size(min = 16,max = 19, message = "Please use 16 to 19 digits")
    private String cardPan;

    @NotBlank(message = "Card CVV is Required")
    @Size(min = 3,max = 3, message = "Please use 3  digits")
    private String cvv;

    @NotBlank(message = "ExpiryMonth is Required")
    @Size(min = 2,max = 2, message = "Please use 2 digits")
     private String expiryMonth;
    @NotBlank(message = "ExpiryMonth is Required")
    @Size(min = 2,max = 2, message = "Please use 2 digits")
    private String expiryYear;
    @NotBlank(message = "CardPin is Required")
    @Size(min = 4,max = 4, message = "Please use 4 digits")
     private String cardPin;
     private String expiryDate;
    @NotBlank(message = "AccountNo is Required")
    @Size(min = 10,max = 10, message = "Please use 10 digits")
     private  String accountNo;

    @NotBlank(message = "AccountType is Required")
    private String accoutType;
    @NotBlank(message = "PaymentMethodTypeCode is Required")
      private String paymentMethodTypeCode;
    @NotBlank(message = "PaymentMethodCode is Required")
     private String paymentMethodCode;
    @NotBlank(message = "CardName is Required")
  private String cardName;

    private User user;


    public  enum TRANSACTION_TYPE {
        createpaymentmethod,dopayment,getpaymentmethods
    }

    public PayphoneRequest() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTtid() {
        return ttid;
    }

    public void setTtid(String ttid) {
        this.ttid = ttid;
    }

    public String getCardPan() {
        return cardPan;
    }

    public String getPaymentMethodTypeCode() {
        return paymentMethodTypeCode;
    }

    public void setPaymentMethodTypeCode(String paymentMethodTypeCode) {
        this.paymentMethodTypeCode = paymentMethodTypeCode;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public void setCardPan(String cardPan) {
        this.cardPan = cardPan;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccoutType() {
        return accoutType;
    }

    public void setAccoutType(String accoutType) {
        this.accoutType = accoutType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
