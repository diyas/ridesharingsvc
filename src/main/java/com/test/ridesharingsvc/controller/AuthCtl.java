package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.AppException;
import com.test.ridesharingsvc.model.Role;
import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.JwtAuthenticationResponse;
import com.test.ridesharingsvc.model.payload.LoginRequest;
import com.test.ridesharingsvc.model.payload.LoginResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.RoleRepo;
import com.test.ridesharingsvc.repository.UsersRepo;
import com.test.ridesharingsvc.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthCtl {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepo userRepository;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
        if (userRepository.existsByUserId(signUpRequest.getUserId())) {
            return new ResponseEntity(new Response(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new Response(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUserId(signUpRequest.getUserId());
        user.setEmail(signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUserId()).toUri();

        return ResponseEntity.created(location).body(new Response(HttpStatus.OK.value(), "User registered successfully"));
    }

    @PostMapping("/signin_user")
    public ResponseEntity<?> userSignIn(@RequestBody User user){
        User result = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword());
        int code = 200;
        String message = "";
        LoginResponse loginResponse = new LoginResponse(true);
        if (result == null){
            code = 400;
            message = "Gagal Login!";
            loginResponse.setLogin(false);
        }
//        if (result.getRoleId() == "DRIVER"){
//
//        } else if(result.getRoleId() == "ADMIN"){
//
//        } else {
//
//        }
        Response resp = new Response();
        resp.setCode(code);
        resp.setMessage(message);
        resp.setData(loginResponse);
        return new ResponseEntity(resp, HttpStatus.resolve(code));
    }
}
