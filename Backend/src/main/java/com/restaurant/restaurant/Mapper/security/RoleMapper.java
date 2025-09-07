package com.restaurant.restaurant.Mapper.security;

import com.restaurant.restaurant.DTO.security.RoleDto;
import com.restaurant.restaurant.model.security.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel ="spring")
public interface RoleMapper {
    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);
}
