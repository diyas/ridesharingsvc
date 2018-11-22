package com.test.ridesharingsvc.model.payload;

import lombok.Data;

@Data
public class LoginRequest {

    private String userName;
    private String password;
}
