package com.restaurant.restaurant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String specialty;
    private String logoPath;
    private String FACEBOOK_LINK;
    private String INSTAGRAM_LINK;
    private String TWITTER_LINK;

}
