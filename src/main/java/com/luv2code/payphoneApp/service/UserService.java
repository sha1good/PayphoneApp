package com.luv2code.payphoneApp.service;

import com.luv2code.payphoneApp.exceptions.UsernameAlreadyExistException;
import com.luv2code.payphoneApp.model.User;
import com.luv2code.payphoneApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //make Sure that the Username is Unique
            newUser.setUsername(newUser.getUsername());
            //Make Sure that password and Confirm password match
            //Make Sure that we do not persist nor Show confirm password
            newUser.setConfirmPassword("");

            return userRepository.save(newUser);

        } catch (Exception e) {
            throw new UsernameAlreadyExistException("Username with '" + newUser.getUsername() + "' already exist");
        }

    }
}


        //catch (Exception e) {
          //  return




