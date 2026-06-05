package com.authapp.projectonauth.controllers;

import com.authapp.projectonauth.dtos.UserDto;
import com.authapp.projectonauth.entities.User;
import com.authapp.projectonauth.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    //create apis
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body((userService.createUser(userDto)));

    }
    //get apis
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    //get users by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    //delete user
    @DeleteMapping("/{userId}")
    public void deleteUser (@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }
    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.updateUser(userDto, userId));

    }
}
