package com.nextgen.user_management_system.service;

import com.nextgen.user_management_system.dto.LoginDto;
import com.nextgen.user_management_system.dto.UserDto;
import com.nextgen.user_management_system.exception.UserException;
import com.nextgen.user_management_system.model.Address;
import com.nextgen.user_management_system.model.Login;
import com.nextgen.user_management_system.model.User;
import com.nextgen.user_management_system.repository.LoginRepository;
import com.nextgen.user_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public ResponseEntity<String> signUp(UserDto userDto) {
        try {
            User user = new User();
            user.setRole(userDto.getRole());
            user.setEmailId(userDto.getEmailId());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setInterests(userDto.getInterest());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNo(userDto.getPhoneNo());
            user.setAboutMe(userDto.getAboutMe());

            Address address = new Address();
            address.setAddress(userDto.getAddress().getAddress());
            address.setAddressType(userDto.getAddress().getAddressType());
            user.setAddress(address);
            this.userRepository.save(user);

            Login login = new Login();
            login.setRole(user.getRole());
            login.setEmailId(user.getEmailId());
            login.setPassword(user.getPassword());
            this.loginRepository.save(login);

            return ResponseEntity.ok("User Registered Successfully !!!");
        }
        catch (Exception e){
            throw new UserException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> signIn(LoginDto loginDto) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmailId(), loginDto.getPassword())
            );
            return new ResponseEntity<>(this.tokenService.generateToken(authentication), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Authentication failed", HttpStatus.FORBIDDEN);
        }
    }
}
