package com.restaurant.restaurant.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.restaurant.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {


    private Long id;
    @NotBlank(message = "category.name.required")
    @Size(min = 2, max = 100, message = "category.name.size")
    private String name;
    private String logo;
    private String flag;
    @JsonIgnore
    private List<Product> products;
}
