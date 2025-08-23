package com.restaurant.restaurant.Service;

import com.restaurant.restaurant.DTO.CategoryDto;
import com.restaurant.restaurant.DTO.ProductDto;

import java.util.List;

public interface CategoryService {
    //basic crud
    CategoryDto save(CategoryDto categoryDto);
    List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtos);
    CategoryDto update(CategoryDto categoryDto);
    List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtos);

    boolean delete(Long id);
    List<Boolean> deleteList(List<Long> ids);

    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();

}
