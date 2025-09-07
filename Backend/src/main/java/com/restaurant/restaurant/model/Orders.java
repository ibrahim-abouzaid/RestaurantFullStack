package com.restaurant.restaurant.model;

import com.restaurant.restaurant.model.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double totalPrice;
    private String totalNumber;

    @ManyToMany
    @JoinTable(
            name = "Order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "prodect_id")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;
}
