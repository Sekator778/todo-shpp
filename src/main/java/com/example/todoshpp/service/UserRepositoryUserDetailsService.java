package com.example.todoshpp.service;

import com.example.todoshpp.model.User;
import com.example.todoshpp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Slf4j
@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * У метода loadByUsername () есть одно простое правило: он никогда не должен возвращать null
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        log.error("detect wrong password or username");
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }
}