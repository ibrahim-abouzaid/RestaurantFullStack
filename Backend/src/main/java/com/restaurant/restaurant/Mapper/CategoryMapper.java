package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.CategoryDto;
import com.restaurant.restaurant.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);
    //CategoryOnlyDto  toCategoryOnlyDto(Category category);

    Category toCategory(CategoryDto categoryDto);
    List<CategoryDto> toListOfCategoryDto(List<Category> categories);

    List<Category> toListOfCategory(List<CategoryDto> categoryDtos);
}
