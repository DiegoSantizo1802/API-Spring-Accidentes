package com.example.BackEnd.PG1.Auth.Controller;


import com.example.BackEnd.PG1.Auth.Service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request){
        final TokenResponse token = authService.register(request);
        return ResponseEntity.ok(token);
    }

   @PostMapping(value = "/login")
    public ResponseEntity<TokenResponse> autenticate(@RequestBody final LoginRequest request){
        final TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }

    /*@PostMapping(value = "/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        return authService.refreshToken(authHeader);
    }*/
}
