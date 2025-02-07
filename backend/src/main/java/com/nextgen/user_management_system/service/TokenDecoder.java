package com.nextgen.user_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenDecoder {

    private final JwtDecoder jwtDecoder;

    public String extractEmailFromToken(String token){

        try{
            Jwt decodeJwt = jwtDecoder.decode(token);

            return decodeJwt.getClaimAsString("email");
        }catch (JwtException ex){
            throw new IllegalStateException("Token Invalid");
        }
    }

    public String getRole(String token){
        try {
            Jwt decodeJwt = jwtDecoder.decode(token);

            return decodeJwt.getClaimAsString("scope");
        }
        catch (JwtException ex){
            throw new IllegalStateException("Token Invalid");
        }
    }

    public boolean isTokenValid(String token){
        try {
            Jwt decodeJwt = jwtDecoder.decode(token);

            return decodeJwt.getExpiresAt().isAfter(Instant.now());
        }
        catch (JwtException ex){
            throw new IllegalStateException("Token Invalid");
        }

    }


}
