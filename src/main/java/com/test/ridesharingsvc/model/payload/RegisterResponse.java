package com.test.ridesharingsvc.model.payload;

import lombok.Data;

@Data
public class RegisterResponse {
    private long id;

    public RegisterResponse(long id){
        this.id = id;
    }
}
