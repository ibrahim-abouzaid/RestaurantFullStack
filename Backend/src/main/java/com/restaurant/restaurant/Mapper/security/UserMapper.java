package com.restaurant.restaurant.Mapper.security;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.controller.vm.security.AuthRequestVm;
import com.restaurant.restaurant.controller.vm.security.AuthResponseVm;
import com.restaurant.restaurant.model.security.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User account);

    User  toUser(UserDto accountDto);

    UserDto toUserDto(AuthRequestVm authRequestVm);

   AuthResponseVm toUserResponseVm(UserDto userDto);
}
