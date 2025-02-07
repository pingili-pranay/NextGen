package com.nextgen.user_management_system.service;

import com.nextgen.user_management_system.dto.LoginDto;
import com.nextgen.user_management_system.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> signUp(UserDto userDto);

    ResponseEntity<String> signIn(LoginDto loginDto);

}
