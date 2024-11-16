package com.example.SpringBootProject.service;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.mapper.UserMapper;
import com.example.SpringBootProject.model.User;
import com.example.SpringBootProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {

        // convert UserDto to Mongo entity
        User use1 = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(use1);
        //convert user Mongo entity to user Dto
        UserDto userDto1 = UserMapper.mapToUserDTo(savedUser);
        return userDto1;

    }

    @Override
    public Optional<UserDto> getUser(String id) {
        User user = userRepository.findById(id).get();
        return Optional.of(UserMapper.mapToUserDTo(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream().map(UserMapper::mapToUserDTo).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existing = userRepository.findById(user.getId()).get();
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        User update = userRepository.save(existing);
        return UserMapper.mapToUserDTo(update);


    }

    @Override
    public String delete(String id) {
        userRepository.deleteById(id);
        return null;

    }


}
