package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.dto.UserDto;
import com.example.SpringBootProject.model.User;
import com.example.SpringBootProject.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    // private RandomId randomId;

    private UserServiceImpl userService;

    @Operation(
            summary = "CRUD REST API",
            description = "This api used to save the record in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "customer record saved successfully in db"
    )
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "CRUD REST API",
            description = "This api used to get  the only one record in database"
    )
    @ApiResponse(
            responseCode = "200 ",
            description = "customer record got successfully from  db"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<UserDto>> getUserById(@PathVariable String id) {
        Optional<UserDto> savedUser = userService.getUser(id);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "CRUD REST API",
            description = "This api used to get all records in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "customer all record got successfully in db"
    )
    @GetMapping("/all-Users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @Operation(
            summary = "CRUD REST API",
            description = "This api used to update the record in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "customer record is updated successfully in db"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> User(@PathVariable String  id, @Valid @RequestBody UserDto user) {
        user.setId(id);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @Operation(
            summary = "CRUD REST API",
            description = "This api used to delete the record in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "customer record is deleted  successfully in db"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable String id){
        userService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }
}
