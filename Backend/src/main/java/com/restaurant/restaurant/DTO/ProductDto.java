package com.restaurant.restaurant.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.restaurant.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    @NotBlank(message = "product.name.required")
    @NotNull(message = "product.name.notNull")
    @Size(min = 2, max = 100, message = "product.name.size")
    private String name;
    @NotBlank(message = "Image URL is required")
    private String image;
    @Size(max = 500, message = "product.description.size")
    private String description;
    @NotNull(message = "Price.required")
    @DecimalMin(value = "0.0", inclusive = false, message = "product.price.less.than.zero")
    private  Double price;
    @NotNull(message = "Category.ID.required")
    private Long categoryId;


}
