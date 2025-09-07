package com.restaurant.restaurant.Service.security;

import com.restaurant.restaurant.DTO.security.RoleDto;
public interface RoleService {
    RoleDto findByRole(String role);
}
