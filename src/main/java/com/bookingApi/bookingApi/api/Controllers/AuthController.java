package com.bookingApi.bookingApi.api.Controllers;

import com.bookingApi.bookingApi.api.DTO.AppUserDto;
import com.bookingApi.bookingApi.api.DTO.AuthReqDto;
import com.bookingApi.bookingApi.api.DTO.AuthResponse;
import com.bookingApi.bookingApi.api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AppUserDto req){
        return new ResponseEntity<>(authService.register(req), HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthReqDto req){
        return new ResponseEntity<>(authService.auth(req), HttpStatus.OK);
    }
}
