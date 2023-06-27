package com.flight.controllers;


import com.flight.entities.EmailDetails;
import com.flight.payload.UserDto;
import com.flight.service.EmailService;
import com.flight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home")
@Controller
public class UserController
{
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    EmailService emailService;
    @Autowired
    EmailDetails emailDetails;

    //CREATE user
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto userRegistered = userService.registerNewUser(userDto);
        EmailDetails emailDetails = EmailDetails.createRegistrationEmail(userDto.getEmail());
        emailService.sendSimpleMail(userDto.getEmail(), emailDetails);
        return new ResponseEntity<>(userRegistered, HttpStatus.CREATED);
    }


    //PUT Update user
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId)
    {
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    //GET Getting user by ID
    @GetMapping("/user/{userId}")
    public  ResponseEntity<UserDto> getUserById(@PathVariable Long userId)
    {
        UserDto getUserById = userService.getUserById(userId);
        return new  ResponseEntity<>(getUserById,HttpStatus.OK);
    }

    //GET Getting user
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        //List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userService.getAllUsers());
    }


    //DELETE Deleting
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted successfully",HttpStatus.OK);
    }
}
