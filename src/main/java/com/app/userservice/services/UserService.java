package com.app.userservice.services;

import com.app.userservice.models.Token;
import com.app.userservice.models.User;
import com.app.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


private BCryptPasswordEncoder encoder ;
private UserRepository  userRepository;

public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository) {
    this.encoder = encoder;
    this.userRepository = userRepository;
}

    public User signUp(String email, String name,  String password ){

    User user = new User();
    user.setEmail(email);
    user.setName(name);
    user.setHashedPassword( encoder.encode( password));
    user.setEmailVerified(true);

// save the user object to the databasae
        return userRepository.save(user);

    }

    public Token login(String email, String password){
        return null;
    }

    public void logout(String token){

    }

    public User validateToken(String token){
        return null;
    }
}
