package com.example.SpringBootProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    @NotEmpty(message = "User first name should not be null")
    private String firstName;
    @NotEmpty(message = "User last name should not be null")
    private String lastName;
    @NotEmpty(message = "User email  should not be null")
    @Email(message = "User email  should be valid")
    private String email;


}
