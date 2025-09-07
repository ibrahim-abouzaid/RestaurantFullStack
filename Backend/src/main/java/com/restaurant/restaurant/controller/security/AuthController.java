package com.restaurant.restaurant.controller.security;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Service.security.AuthService;

import com.restaurant.restaurant.controller.vm.security.AuthRequestVm;
import com.restaurant.restaurant.controller.vm.security.AuthResponseVm;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseVm> signUp(@RequestBody @Valid UserDto userDto) throws SystemException {
        return ResponseEntity.ok().body(authService.signUp(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseVm> login(@RequestBody @Valid AuthRequestVm accountAuthRequestVm) throws SystemException {
        return ResponseEntity.ok(authService.login(accountAuthRequestVm));
    }
}