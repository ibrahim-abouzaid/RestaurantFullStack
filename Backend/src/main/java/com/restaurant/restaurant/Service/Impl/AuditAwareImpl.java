package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    private UserService userService;
    @Autowired
    public AuditAwareImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Optional<String> getCurrentAuditor() {
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(userDto.getId().toString());
    }
}
