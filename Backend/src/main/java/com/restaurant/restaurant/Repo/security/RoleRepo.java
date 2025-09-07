package com.restaurant.restaurant.Repo.security;


import com.restaurant.restaurant.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
