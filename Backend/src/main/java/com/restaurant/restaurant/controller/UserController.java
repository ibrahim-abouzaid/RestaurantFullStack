package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Service.security.UserService;
import com.restaurant.restaurant.controller.vm.UserChangePasswordRequestVm;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController()
public class UserController {

    private UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/Change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody @Valid UserChangePasswordRequestVm userChangePasswordRequestVm ) throws SystemException {

        return ResponseEntity.ok().body(userService.changePassword(userChangePasswordRequestVm));
    }
    @GetMapping("/get-all-User")
    public ResponseEntity<List<UserDto>> getAllUser(){
    return ResponseEntity.ok().body(userService.getUsers());
    }
}
