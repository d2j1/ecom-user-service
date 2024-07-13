package com.app.userservice.security.service;

import com.app.userservice.models.User;
import com.app.userservice.repository.UserRepository;
import com.app.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("user with email " +username+ "does not exist");
        }

        return new CustomUserDetails(user.get());
    }
}
