package com.flight.service.impl;

import com.flight.config.ConstantValue;
import com.flight.entities.Role;
import com.flight.entities.User;
import com.flight.exceptions.ResourceNotFoundException;
import com.flight.payload.UserDto;
import com.flight.repository.RoleRepo;
import com.flight.repository.UserRepository;
import com.flight.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=  this.modelMapper.map(userDto, User.class);
       // user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role =  this.roleRepo.findById(ConstantValue.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }


    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id",userId)));
       // User update=dtoToUser(userDto);
        User update = modelMapper.map(userDto, User.class);
        update.setId(userId);
        User  updated= userRepo.save(update);
        //return userToDto(updated);
        return modelMapper.map(updated, UserDto.class);

    }

    @Override
    public UserDto getUserById(Long userId)
    {
        User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id",userId)));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List <User> user = userRepo.findAll();
        List<UserDto> userDto = user.stream().map(l-> modelMapper.map(l,UserDto.class)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User","Id",userId)));
        userRepo.deleteById(userId);
    }


}

