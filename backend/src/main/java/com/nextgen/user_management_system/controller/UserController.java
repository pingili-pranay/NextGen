package com.nextgen.user_management_system.controller;

import com.nextgen.user_management_system.model.Login;
import com.nextgen.user_management_system.model.User;
import com.nextgen.user_management_system.repository.LoginRepository;
import com.nextgen.user_management_system.repository.UserRepository;
import com.nextgen.user_management_system.service.TokenDecoder;
import com.nextgen.user_management_system.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final TokenDecoder tokenDecoder;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){

        this.userRepository.save(user);
        Login login = new Login();
        login.setRole(user.getRole());
        login.setEmailId(user.getEmailId());
        login.setPassword(passwordEncoder.encode(user.getPassword()));
        this.loginRepository.save(login);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/verify")
    public String verify(Authentication authentication){
        System.out.println(authentication.getAuthorities());
        return "Logged In";
    }

    @GetMapping("/verifyAdmin")
    public String verifyAdmin(@RequestHeader("Authorization") String authorization){

        return tokenDecoder.extractEmailFromToken(authorization.substring("Bearer ".length())) + " "
                + tokenDecoder.getRole(authorization.substring("Bearer ".length())) + " "
                + tokenDecoder.isTokenValid(authorization.substring("Bearer ".length()));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){

        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmailId(), login.getPassword())
            );
            return new ResponseEntity<>(this.tokenService.generateToken(authentication), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Authentication failed", HttpStatus.FORBIDDEN);
        }
    }

}