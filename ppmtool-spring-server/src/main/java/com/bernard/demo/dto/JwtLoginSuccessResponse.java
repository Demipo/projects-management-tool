package com.bernard.demo.dto;

import lombok.*;

@Getter @Setter @ToString
public class JwtLoginSuccessResponse {
    private boolean success;
    private String token;

    public JwtLoginSuccessResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }
}
