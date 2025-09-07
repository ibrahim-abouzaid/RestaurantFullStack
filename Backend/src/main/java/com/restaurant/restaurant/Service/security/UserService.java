package com.restaurant.restaurant.Service.security;


import com.restaurant.restaurant.DTO.security.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto createUser(UserDto accountDto);

    UserDto updateUser(UserDto accountDto);

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

}