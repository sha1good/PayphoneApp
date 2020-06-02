package com.luv2code.payphoneApp.controller;

import com.luv2code.payphoneApp.model.User;
import com.luv2code.payphoneApp.payload.JWTLoginResponse;
import com.luv2code.payphoneApp.payload.LoginRequest;
import com.luv2code.payphoneApp.security.JWTTokenProvider;
import com.luv2code.payphoneApp.service.MapValidationError;
import com.luv2code.payphoneApp.service.UserService;
import com.luv2code.payphoneApp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.luv2code.payphoneApp.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
     @Autowired
    private MapValidationError mapValidationError;

     @Autowired
    private UserValidator userValidator;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

     @PostMapping("/register")
     public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){

         ////Validate that the password Match and Username
         userValidator.validate(user,result);

         ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
         if(errorMap !=null) return errorMap;



         User userSaved = userService.saveUser(user);
         return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
     }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              BindingResult result){

        ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
        if(errorMap !=null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String AccessToken = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JWTLoginResponse(true, AccessToken));
    }


}
