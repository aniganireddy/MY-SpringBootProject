package com.example.SpringBootProject.service;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto);
    Optional<UserDto> getUser(String id );
    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);
    String delete(String id);
}
