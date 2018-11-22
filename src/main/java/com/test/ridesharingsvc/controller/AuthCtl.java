package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.LoginResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthCtl {

    @Autowired
    UsersRepo users;

    @PostMapping("/signin_user")
    public ResponseEntity<?> userSignIn(@RequestBody User user){
        User result = users.findByUserIdAndPassword(user.getUserId(), user.getPassword());
        int code = 200;
        String message = "";
        LoginResponse loginResponse = new LoginResponse(true);
        if (result == null){
            code = 400;
            message = "Gagal Login!";
            loginResponse.setLogin(false);
        }
        Response resp = new Response();
        resp.setCode(code);
        resp.setMessage(message);
        resp.setData(loginResponse);
        return new ResponseEntity(resp, HttpStatus.resolve(code));
    }
}
