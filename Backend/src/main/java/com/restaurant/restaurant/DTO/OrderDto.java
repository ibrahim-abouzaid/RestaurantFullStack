package com.restaurant.restaurant.DTO;

import com.restaurant.restaurant.model.Product;
import com.restaurant.restaurant.model.security.User;
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
public class OrderDto {

    private Long id;
    private String code;
    private Double totalPrice;
    private Double totalNumber;
    private List<ProductDto> products;
}
