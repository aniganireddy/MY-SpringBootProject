package com.example.SpringBootProject.mapper;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class UserMapper {
    //comvert mongo entity to user DTo
    public static UserDto mapToUserDTo(User user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
        return userDto;

    }

    //convert UserDto to Mongo entity
    public static User mapToUser(UserDto userDto){
        User user = new User(userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail());

        return user;
    }
}
