package com.authapp.projectonauth.services.impl;

import com.authapp.projectonauth.dtos.UserDto;
import com.authapp.projectonauth.entities.Provider;
import com.authapp.projectonauth.entities.User;
import com.authapp.projectonauth.exceptions.ResourceNotFoundException;
import com.authapp.projectonauth.helpers.UserHelper;
import com.authapp.projectonauth.repositories.UserRepository;
import com.authapp.projectonauth.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if(userDto.getEmail()==null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        User user=modelMapper.map(userDto, User.class);

        user.setProvider(user.getProvider()!=null ? userDto.getProvider(): Provider.LOCAL);
        User saveduser= userRepository.save(user);
        return modelMapper.map(saveduser,UserDto.class);

    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user=userRepository.findByEmail(email)
                 .orElseThrow(()-> new ResourceNotFoundException("User not found with given email id"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID uid= UserHelper.parseUUID(userId);
        User existingUser=userRepository.findById(uid).orElseThrow(() ->new ResourceNotFoundException("user not found with given id"));
        if (userDto.getName() != null) existingUser.setName(userDto.getName());
        if (userDto.getImage() != null) existingUser.setImage(userDto.getImage());
        if (userDto.getProvider() != null) existingUser.setProvider(userDto.getProvider());
        //TODO: change password updation logic...
        if (userDto.getPassword() != null) existingUser.setPassword(userDto.getPassword());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdatedAt(Instant.now());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uid= UserHelper.parseUUID(userId);
        User user=userRepository.findById(uid).orElseThrow(() ->new ResourceNotFoundException("user not found with given id"));


    }

    @Override
    public UserDto getUserById(String userId){
       User user= userRepository.findById(UserHelper.parseUUID(userId)).orElseThrow(()->new ResourceNotFoundException("Uswr found with the given id"));
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        //we can pass list and all so used iterable
        return userRepository.findAll()
                .stream()
                .map((element) -> modelMapper.map(element, UserDto.class)).toList();
    }
}
