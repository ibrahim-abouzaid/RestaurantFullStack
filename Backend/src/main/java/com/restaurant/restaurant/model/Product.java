package com.restaurant.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String image;
    @Column(length = 500)
    private String description;
    private  Double price;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



}
