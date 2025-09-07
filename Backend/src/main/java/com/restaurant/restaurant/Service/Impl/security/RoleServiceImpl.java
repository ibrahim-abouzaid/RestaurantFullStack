package com.restaurant.restaurant.Service.Impl.security;


import com.restaurant.restaurant.DTO.security.RoleDto;
import com.restaurant.restaurant.Mapper.security.RoleMapper;
import com.restaurant.restaurant.Repo.security.RoleRepo;
import com.restaurant.restaurant.Service.security.RoleService;
import com.restaurant.restaurant.model.security.Role;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepo roleRepo;

    private RoleMapper roleMapper;
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo, RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto findByRole(String role) {
        try {
           Role roleResult = roleRepo.findByRole(role);
            if (roleResult == null) {
                throw new SystemException("error.role.not.found");
            }
            return roleMapper.toRoleDto(roleResult);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
