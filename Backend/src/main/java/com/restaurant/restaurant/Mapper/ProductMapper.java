package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") // "spring" so it works with @Autowired
public interface ProductMapper {


    //Entity -> DTO
    @Mapping(source = "category.id", target = "category_id")
    ProductDto toProductDto(Product product);
    // DTO -> Entity
    @Mapping(source = "category_id", target = "category.id")
    Product toProduct (ProductDto productDto);
    //List of Entity -> list of DTO
    List<Product> toListOfProduct(List<ProductDto>productDtos);
    // List of DTO -> List of Entity
    List<ProductDto> toListOfProductDto(List<Product> products);

}
