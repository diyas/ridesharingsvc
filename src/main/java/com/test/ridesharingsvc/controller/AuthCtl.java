package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.model.RoleName;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.LoginRequest;
import com.test.ridesharingsvc.repository.RoleRepo;
import com.test.ridesharingsvc.repository.UsersRepo;
import com.test.ridesharingsvc.security.JwtTokenProvider;
import com.test.ridesharingsvc.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthCtl {

    @Autowired
    AuthService authService;

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

        return authService.authenticateUser(loginRequest);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Token Generated.", new JwtAuthenticationResponse(jwt)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {

        return authService.registerUser(signUpRequest, RoleName.USER);
//        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
//            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), "Email Address already in use!"));
//        }
//
//        // Creating user's account
//        User user = new User();
//        user.setName(signUpRequest.getName());
//        user.setUserName(signUpRequest.getUserName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setStsUsr("0");
//        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//
//        Role userRole = roleRepository.findByName(RoleName.USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
//
//        User result = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
//                .buildAndExpand(result.getUserId()).toUri();
//
//        return ResponseEntity.created(location).body(new Response(HttpStatus.OK.value(), "User registered successfully"));
    }
    @PostMapping("/register_driver")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody User signUpRequest) {
        return authService.registerUser(signUpRequest, RoleName.DRIVER);
    }

}
