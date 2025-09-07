package com.restaurant.restaurant.Service.security;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.controller.vm.security.AuthRequestVm;
import com.restaurant.restaurant.controller.vm.security.AuthResponseVm;
;

public interface AuthService {
   AuthResponseVm signUp(UserDto userDto);

    AuthResponseVm login(AuthRequestVm authRequestVm);
}