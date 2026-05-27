package com.urbanroute.controller;

import com.urbanroute.dto.request.UserLoginRequestDto;
import com.urbanroute.dto.request.UserRegisterRequestDto;
import com.urbanroute.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@Valid @RequestBody UserRegisterRequestDto registerDto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody UserLoginRequestDto loginDto){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.login(loginDto));
    }
}
