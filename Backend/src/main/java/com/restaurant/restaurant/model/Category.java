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
public class Category extends BaseEntity {

    private String name;
    private String logo;
    private String flag;
    @OneToMany(mappedBy = "category")
    @OrderBy("id ASC")
    private List<Product> products;

}
