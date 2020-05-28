package com.bernard.demo.services;

import com.bernard.demo.domains.User;
import com.bernard.demo.exceptions.UserAlreadyExistExceptionHandler;
import com.bernard.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveUser (User newUser){

        try{
            if(userRepository.findByUsername(newUser.getUsername()) == null)
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);

        }catch(Exception e){
            throw new UserAlreadyExistExceptionHandler("User '" + newUser.getUsername() + "'already exist");
        }

    }
}
