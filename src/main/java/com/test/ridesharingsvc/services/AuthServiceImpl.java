package com.test.ridesharingsvc.services;

import com.test.ridesharingsvc.exception.AppException;
import com.test.ridesharingsvc.model.Role;
import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.JwtAuthenticationResponse;
import com.test.ridesharingsvc.model.payload.LoginRequest;
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
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService{
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

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Token Generated.", new JwtAuthenticationResponse(jwt)));
    }

    public ResponseEntity<?> registerUser(User signUpRequest, RoleName role) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"));
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setStsUsr("0");
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUserId()).toUri();

        return ResponseEntity.created(location).body(new Response(HttpStatus.OK.value(), "User registered successfully"));
    }
}
