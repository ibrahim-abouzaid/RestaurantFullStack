package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.CategoryDto;
import com.restaurant.restaurant.DTO.ChefDto;
import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Chef;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChefMapper {


    ChefDto toChefDto(Chef chef);
    Chef toChef(ChefDto chefDto);

    List<ChefDto> toListOfChefDto(List<Chef> chefs);
    List<Chef> toListOfChef(List<ChefDto> chefDtos);

}
