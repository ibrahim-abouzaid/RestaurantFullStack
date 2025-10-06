package com.restaurant.restaurant.model;

import com.restaurant.restaurant.base.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {



    private String name;

    private String image;
    @Column(length = 500)
    private String description;
    private  Double price;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OrderBy("id ASC")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;






}
