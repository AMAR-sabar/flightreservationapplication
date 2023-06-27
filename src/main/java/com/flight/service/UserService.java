package com.flight.service;

import com.flight.payload.UserDto;

import java.util.List;

public interface UserService
{
    UserDto registerNewUser(UserDto userDto);

    UserDto updateUser(UserDto userDto , Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);


}
