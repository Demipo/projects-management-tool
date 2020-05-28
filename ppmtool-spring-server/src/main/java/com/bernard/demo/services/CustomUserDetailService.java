package com.bernard.demo.services;

import com.bernard.demo.domains.User;
import com.bernard.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            new UsernameNotFoundException("User '"+ username + "' not found");
        return user;
    }

    @Transactional
    public Optional<User> loadUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user == null)
            new UsernameNotFoundException("User not found");
        return user;
    }
}
