package com.restaurant.restaurant.Service.security;


import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.controller.vm.UserChangePasswordRequestVm;
import jakarta.transaction.SystemException;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);
    Boolean changePassword(UserChangePasswordRequestVm userChangePasswordRequestVm) throws SystemException;

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

}