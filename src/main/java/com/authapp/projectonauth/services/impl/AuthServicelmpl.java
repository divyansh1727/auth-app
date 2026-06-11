package com.authapp.projectonauth.services.impl;

import com.authapp.projectonauth.dtos.UserDto;
import com.authapp.projectonauth.entities.User;
import com.authapp.projectonauth.services.AuthService;
import com.authapp.projectonauth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServicelmpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }
}
