package com.test.ridesharingsvc.model.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean isLogin;
    public LoginResponse(boolean isLogin){
        this.isLogin = isLogin;
    }


}
