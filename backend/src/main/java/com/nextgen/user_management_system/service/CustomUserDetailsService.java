package com.nextgen.user_management_system.service;

import com.nextgen.user_management_system.model.Login;
import com.nextgen.user_management_system.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = this.loginRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found!!!"));

        return User.builder()
                .authorities(List.of(new SimpleGrantedAuthority(login.getRole())))
                .username(login.getEmailId())
                .password(login.getPassword())
                .build();
    }
}
