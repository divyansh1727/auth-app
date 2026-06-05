package com.authapp.projectonauth.services;

import com.authapp.projectonauth.dtos.UserDto;

public interface AuthService {
    UserDto registerUser(UserDto userDto);
}
