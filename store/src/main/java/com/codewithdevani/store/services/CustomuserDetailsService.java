package com.codewithdevani.store.services;

import com.codewithdevani.store.entity.UserEntity;
import com.codewithdevani.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomuserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Fetch user from Database
        UserEntity user=userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found."));

        return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
    }
}
