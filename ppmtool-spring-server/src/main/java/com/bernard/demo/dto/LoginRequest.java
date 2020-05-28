package com.bernard.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

}
