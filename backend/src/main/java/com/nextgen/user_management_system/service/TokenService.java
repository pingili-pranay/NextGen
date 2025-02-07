package com.nextgen.user_management_system.service;

import com.nextgen.user_management_system.model.Login;
import com.nextgen.user_management_system.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final LoginRepository loginRepository;

    Instant instant = Instant.now();

    public String generateToken(Authentication authentication){

        Login login = this.loginRepository.findById(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not in database"));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("Next Gen")
                .issuedAt(instant)
                .claim("email", login.getEmailId())
                .claim("scope", login.getRole())
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

    }

}
