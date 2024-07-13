package com.app.userservice.controller;

import com.app.userservice.dtos.LogOutRequestDto;
import com.app.userservice.dtos.LoginRequestDto;
import com.app.userservice.dtos.SignUpRequestDto;
import com.app.userservice.dtos.UserDto;
import com.app.userservice.models.Token;
import com.app.userservice.models.User;
import com.app.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return null;
}

@PostMapping("/validate/{token}")
public UserDto validateToken(@PathVariable String token){

        return null;
}

@GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable long id){
        return null;
}
}
