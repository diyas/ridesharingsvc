package com.test.ridesharingsvc.services;

import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.LoginRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    public ResponseEntity<?> registerUser(User signUpRequest, RoleName role);

}
