package com.app.userservice.services;

import com.app.userservice.models.Token;
import com.app.userservice.models.User;
import com.app.userservice.repository.TokenRepository;
import com.app.userservice.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {


private BCryptPasswordEncoder encoder ;
private UserRepository  userRepository;
    private final TokenRepository tokenRepository;

    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository,
                       TokenRepository tokenRepository) {
    this.encoder = encoder;
    this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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

        Optional<User> userOpt = userRepository.findByEmail(email);

        if( userOpt.isEmpty() ){
        throw new UsernameNotFoundException("Email does not exist : "+email);
        }


        User user = userOpt.get();

        if( !encoder.matches(password, user.getHashedPassword()))
        {
            // return null tokem

            return null;

        }
            // login successful
            // generate the token
            Token token = generateToken(user);

            // save token in the repository
            Token savedToken = tokenRepository.save(token);

        return savedToken;
    }

    private Token generateToken(User user){

        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);

        Date expiry = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());


                Token token = new Token();
                token.setExpiryAt(expiry);
                // 128 character alpha numeric string
                token.setToken(RandomStringUtils.randomAlphanumeric(128));
                token.setUser(user);

        return token;

    }
    public void logout(String token){

    }

    public User validateToken(String token){
        return null;
    }
}
