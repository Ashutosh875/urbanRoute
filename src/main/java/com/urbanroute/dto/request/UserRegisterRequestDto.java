package com.urbanroute.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {

    @NotBlank(message = "Name is Required field")
    private String name;

    @NotBlank(message = "Email is Required field")
    @Email(message = "Enter a valid email format")
    private String email;

    @NotBlank(message = "Password is required field")
    @Size(min = 6 , message = "password must be at least 6 characters")
    private String password;

}
