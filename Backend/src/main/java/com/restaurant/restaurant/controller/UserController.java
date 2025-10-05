package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.Service.security.UserService;
import com.restaurant.restaurant.controller.vm.UserChangePasswordRequestVm;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
