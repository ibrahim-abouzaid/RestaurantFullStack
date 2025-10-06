package com.restaurant.restaurant.model.security;

import com.restaurant.restaurant.base.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetails extends BaseEntity {

    @Column(nullable = false)
    private String age;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @OneToOne
    private User user;
}