package com.luv2code.payphoneApp.service;


import com.luv2code.payphoneApp.model.User;
import com.luv2code.payphoneApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomeUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found !");
        }
        return user;
    }

    @Transactional
    public User loadUserById(Long id){

        User user = userRepository.getUserById(id);
        if(user ==null){
            throw new UsernameNotFoundException("User with '" + id + " ' Not Found");
        }

        return user;
    }
}
