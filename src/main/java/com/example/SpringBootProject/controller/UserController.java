package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.model.User;
import com.example.SpringBootProject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    // private RandomId randomId;

    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable String id) {
        Optional<UserDto> savedUser = userService.getUser(id);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }


    @GetMapping("/all-Users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> User(@PathVariable String  id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable String id){
        userService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }
}
