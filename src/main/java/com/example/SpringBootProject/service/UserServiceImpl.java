package com.example.SpringBootProject.service;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.exception.EmailAlreadyExists;
import com.example.SpringBootProject.exception.ResourceNotFoundException;
import com.example.SpringBootProject.mapper.UserMapper;
import com.example.SpringBootProject.model.User;
import com.example.SpringBootProject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        // convert UserDto to Mongo entity
        // User use1 = UserMapper.mapToUser(userDto);
        //using model mapper
        Optional<User>optionalUser= userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExists("Email already exists for user");
        }
        User use1 = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(use1);
        //convert user Mongo entity to user Dto
        //UserDto userDto1 = UserMapper.mapToUserDTo(savedUser);
        // using the model mapper
        UserDto userDto1 = modelMapper.map(savedUser, UserDto.class);
        return userDto1;

    }

    @Override
    public Optional<UserDto> getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        //return Optional.of(UserMapper.mapToUserDTo(user));
        // using the model mapper
        return Optional.of(modelMapper.map(user, UserDto.class));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        // return user.stream().map(UserMapper::mapToUserDTo).collect(Collectors.toList());
        //using the model mapper
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existing = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException(user.getId())
        );
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        User update = userRepository.save(existing);
        // return UserMapper.mapToUserDTo(update);
        //using the model mapper
        return modelMapper.map(update, UserDto.class);

    }

    @Override
    public String delete(String id) {
        User existing = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id)
        );
        userRepository.deleteById(id);
        return null;

    }


}
