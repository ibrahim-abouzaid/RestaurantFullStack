package com.restaurant.restaurant.model.security;

import com.restaurant.restaurant.base.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
