package com.authapp.projectonauth.services.impl;

import com.authapp.projectonauth.dtos.UserDto;
import com.authapp.projectonauth.entities.User;
import com.authapp.projectonauth.services.AuthService;
import com.authapp.projectonauth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServicelmpl implements AuthService {
    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserDto userDto1=userService.createUser(userDto);
        return userDto1;
    }
}
