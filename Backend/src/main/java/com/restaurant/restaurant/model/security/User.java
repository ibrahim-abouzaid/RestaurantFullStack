package com.restaurant.restaurant.model.security;

import com.restaurant.restaurant.base.model.BaseEntity;
import com.restaurant.restaurant.model.ContactInfo;

import com.restaurant.restaurant.model.Orders;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;
    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(

            name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<ContactInfo> contacts;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

}
