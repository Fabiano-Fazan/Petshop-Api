package com.petshop.api.controller;

import com.petshop.api.dto.response.AuthResponseDto;
import com.petshop.api.dto.request.LoginDto;
import com.petshop.api.dto.request.RegisterDto;
import com.petshop.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto registerDTO){return ResponseEntity.ok(authService.register(registerDTO));}

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDTO){return ResponseEntity.ok(authService.login(loginDTO));}



}
