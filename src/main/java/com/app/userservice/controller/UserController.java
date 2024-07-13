package com.app.userservice.controller;

import com.app.userservice.dtos.LogOutRequestDto;
import com.app.userservice.dtos.LoginRequestDto;
import com.app.userservice.dtos.SignUpRequestDto;
import com.app.userservice.dtos.UserDto;
import com.app.userservice.models.Token;
import com.app.userservice.models.User;
import com.app.userservice.repository.TokenRepository;
import com.app.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/signup")
public UserDto signUp(@RequestBody SignUpRequestDto requestDto){

        User user = userService.signUp(
                requestDto.getEmail(),
                requestDto.getName(),
                requestDto.getPassword()
        );

    return UserDto.from(user);
}

@PostMapping("/login")
public Token login(@RequestBody LoginRequestDto loginDto){

        Token token = userService.login(loginDto.getEmail(), loginDto.getPassword());

        return token;
}

@PostMapping("/logout")
public ResponseEntity<Void>  logOut(@RequestBody LogOutRequestDto logoutDto){
        userService.logout(logoutDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
}

@GetMapping("/validate/{token}")
public UserDto validateToken(@PathVariable String token){

//        System.out.println("Validate token has been called for token "+  token);

        return UserDto.from(userService.validateToken(token));

    }

@GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable long id){
        return null;
}
}
