package com.test.ridesharingsvc.model.payload;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;

    public RegisterResponse(Long id){
        this.id = id;
    }
}
