package com.luv2code.payphoneApp.controller;


import com.luv2code.payphoneApp.exceptions.PayphoneException;
import com.luv2code.payphoneApp.exceptions.PayphoneIdException;
import com.luv2code.payphoneApp.model.Paymentmethods;
import com.luv2code.payphoneApp.model.PayphoneRequest;
import com.luv2code.payphoneApp.model.PayphoneResponse;
import com.luv2code.payphoneApp.service.MapValidationError;
import com.luv2code.payphoneApp.service.PayphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
@RequestMapping("/api/payphone")
@CrossOrigin
public class PayphoneController {

    @Autowired
    private PayphoneService payphoneService;

    @Autowired
    private MapValidationError mapValidationError;

    @PostMapping("/createPaymentMethod")
    public ResponseEntity<?> createPaymentMethod(@Valid @RequestBody PayphoneRequest payphoneRequest,
                                                 BindingResult result
                                                 ) throws IOException, NoSuchAlgorithmException {

        ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
        if(errorMap !=null) return errorMap;

        String payRequest = payphoneService.CreatePaymentMethod(payphoneRequest);
        System.out.println("Payphone Request " + payRequest);

        if(payRequest ==null || equals("")){
           throw  new PayphoneException("Server Error", "unable to Create Card","VerveMpin Pod is not Reachable",
                   "Please reachout to Implementation Enginners for help !");
        }

        return  new ResponseEntity<String>(payRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getPaymentMethod(@PathVariable String phoneNumber) throws IOException, NoSuchAlgorithmException {

        PayphoneResponse payphoneResponse = payphoneService.getPaymentMethod(phoneNumber);
           if(payphoneResponse.getPaymentmethods() ==null){
               throw  new PayphoneIdException("Unable to retrieve your card, please check the " + phoneNumber +  " passed");
           }

        return  new ResponseEntity<PayphoneResponse>(payphoneResponse, HttpStatus.OK);
    }

}
