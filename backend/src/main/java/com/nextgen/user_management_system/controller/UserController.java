package com.nextgen.user_management_system.controller;

import com.nextgen.user_management_system.dto.LoginDto;
import com.nextgen.user_management_system.dto.UserDto;
import com.nextgen.user_management_system.service.TokenDecoder;
import com.nextgen.user_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){

        return this.userService.signUp(userDto);

    }

    @GetMapping("/verifyAdmin")
    public String verify(Authentication authentication){
        return "Logged In";
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto){

        return this.userService.signIn(loginDto);

    }

}