package com.restaurant.restaurant.Service.Impl.security;

import com.restaurant.restaurant.DTO.security.RoleDto;
import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Mapper.security.UserMapper;
import com.restaurant.restaurant.Service.security.AuthService;
import com.restaurant.restaurant.Service.security.UserService;


import com.restaurant.restaurant.Service.token.TokenHandler;
import com.restaurant.restaurant.controller.vm.security.AuthRequestVm;
import com.restaurant.restaurant.controller.vm.security.AuthResponseVm;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;

    private TokenHandler tokenHandler;

    private PasswordEncoder passwordEncoder;



    @Autowired
    public AuthServiceImpl(UserService accountService, TokenHandler tokenHandler, PasswordEncoder passwordEncoder) {
        this.userService = accountService;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;

    }

    //TODO add transactional annotation
    @Override
    public AuthResponseVm signUp(UserDto userDto) {

        UserDto user = userService.createUser(userDto);
        if(Objects.isNull(user)){
            throw new RuntimeException("create.failed");
        }
        List<String> roles= new ArrayList<>();
        for(RoleDto roleDto : user.getRoles()){
            roles.add(roleDto.getRole());
        }
        return new AuthResponseVm(user.getId(),user.getUsername(),tokenHandler.createToken(user),roles);
    }

    @Override
    public AuthResponseVm login(AuthRequestVm accountAuthRequestVm) {
        try {
            UserDto userDto = userService.getUserByUsername(accountAuthRequestVm.getUsername());
            if (Objects.isNull(userDto)) {
                throw new SystemException("user.not.found");
            }
            if (!passwordEncoder.matches(accountAuthRequestVm.getPassword(), userDto.getPassword())) {
                throw new SystemException("error.invalid.credentials");
            }
            List<String> roles= new ArrayList<>();
            for(RoleDto roleDto : userDto.getRoles()){
                roles.add(roleDto.getRole());
            }
            return new AuthResponseVm(userDto.getId(),userDto.getUsername(),tokenHandler.createToken(userDto),roles);
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}