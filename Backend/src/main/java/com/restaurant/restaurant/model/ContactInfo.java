package com.restaurant.restaurant.model;

import com.restaurant.restaurant.base.model.BaseEntity;
import com.restaurant.restaurant.model.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo extends BaseEntity {

    private String name;
    private String email;
    private String subject;
    @Column(length = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
