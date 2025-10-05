package com.restaurant.restaurant.Service.Impl.security;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Mapper.security.RoleMapper;
import com.restaurant.restaurant.Mapper.security.UserMapper;
import com.restaurant.restaurant.Repo.security.UserRepo;
import com.restaurant.restaurant.Service.security.RoleService;
import com.restaurant.restaurant.Service.security.UserService;
import com.restaurant.restaurant.controller.vm.UserChangePasswordRequestVm;
import com.restaurant.restaurant.model.Enums.Roles;
import com.restaurant.restaurant.model.security.Role;
import com.restaurant.restaurant.model.security.User;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private UserMapper userMapper;

    private RoleMapper roleMapper;
@Autowired
    public UserServiceImpl(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder, RoleService roleService, UserMapper userMapper, RoleMapper roleMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<UserDto> getUsers() {
        try {
            List<User> users = userRepo.findAll();
            if (users.isEmpty()) {
                throw new SystemException("empty.users");
            }
            return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if(Objects.nonNull(userDto.getId())){
            throw new RuntimeException("id.must_be.null");
        }
        User user =userRepo.findByUsername(userDto.getUsername());
        if(Objects.nonNull(user)){
            throw new RuntimeException("user.found");
        }

            user = userMapper.toUser(userDto);
            //encode password
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            //make relation between user and role
            user = initRoleToUser(user);
            user = userRepo.save(user);
            return userMapper.toUserDto(user);


    }
    private User initRoleToUser(User user) {
        Role role = roleMapper.toRole(roleService.findByRole(Roles.USER.toString()));
        List<Role> roles = user.getRoles();
        if (Objects.isNull(roles)) {
            roles = new ArrayList<>();
        }
        roles.add(role);
         user.setRoles(roles);
         return user;
    }



    private void validateCreateUser(UserDto userDto) throws SystemException {
        if (Objects.nonNull(userDto.getId())) {
            throw new SystemException("id.must_be.null");
        }
        if (Objects.nonNull(getUserByUsername(userDto.getUsername()))) {
            throw new SystemException("user.exists");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        try {
            validateUpdateUser(userDto.getId());
            User user = userMapper.toUser(userDto);
            user = userRepo.save(user);
            return userMapper.toUserDto(user);
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean changePassword(UserChangePasswordRequestVm userChangePasswordRequestVm) throws SystemException {
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if(!passwordEncoder.matches(userChangePasswordRequestVm.getCurrentPassword(), userDto.getPassword())){

           throw new SystemException("error.invalid.credentials");

        }
       if(!userChangePasswordRequestVm.getNewPassword().equals(userChangePasswordRequestVm.getConfirmNewPassword())){
           throw new SystemException("error.password.not.match");
       }
        userDto.setPassword(passwordEncoder.encode(userChangePasswordRequestVm.getNewPassword()));

        User user= userRepo.save( userMapper.toUser(userDto));
        if(Objects.isNull(user)){
            return false;
        }
        return true;
    }

    private void validateUpdateUser(Long id) throws SystemException {
        if (Objects.isNull(id)) {
            throw new SystemException("id.must_be.not_null");
        }
        if (Objects.isNull(getUserById(id))) {
            throw new SystemException("not_found.user");
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            validateUpdateUser(id);
            userRepo.deleteById(id);
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        try {
            if (Objects.isNull(id)) {
                throw new SystemException("id.must_be.not_null");
            }
            Optional<User> result = userRepo.findById(id);
            if (result.isEmpty()) {
                throw new SystemException("not_found.user");
            }
            return userMapper.toUserDto(result.get());
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDto getUserByUsername(String username) {
        try {
            if (username.isEmpty()) {
                throw new SystemException("not_empty.name");
            }
            User result = userRepo.findByUsername(username);
            if (result == null) {
                return null;
            }
            return userMapper.toUserDto(result);
        } catch (SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
