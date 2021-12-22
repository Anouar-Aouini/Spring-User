package com.fivepoints.spring.controllers;

import com.fivepoints.spring.exceptions.EmailAlreadyUsedException;
import com.fivepoints.spring.payload.requests.LoginRequest;
import com.fivepoints.spring.payload.requests.RegisterRequest;
import com.fivepoints.spring.payload.responses.LoginResponse;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        String token = this.authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token,"Bearer", "Login successfully",loginRequest.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyUsedException {
        String message = this.authService.register(registerRequest);
        return ResponseEntity.ok(new MessageResponse(message));
    }
}
