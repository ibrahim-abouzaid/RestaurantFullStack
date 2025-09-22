package com.restaurant.restaurant.controller.vm.security;

import com.restaurant.restaurant.DTO.security.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthResponseVm {
    private Long id;
    private String username;
    private String token;
    private List<String> roles;


}