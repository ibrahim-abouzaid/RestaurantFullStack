package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.CategoryDto;
import com.restaurant.restaurant.Mapper.CategoryMapper;
import com.restaurant.restaurant.Repo.CategoryRepo;
import com.restaurant.restaurant.Service.CategoryService;
import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if(Objects.nonNull(categoryDto.getId())){
            //TODO handel the message
            throw new RuntimeException("ID must be null");
        }
        Category category= categoryMapper.toCategory(categoryDto);
        //TODO will cause exception
        //TODO when save product save Category

        category=categoryRepo.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;

    }

    @Override
    public List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtos) {
        categoryDtos.stream().forEach(categoryDto -> {
            if(Objects.nonNull(categoryDto.getId())){
                //TODO handel the message
                throw new RuntimeException("ID must be null");
            }
        });

       List<Category> categories= categoryRepo.saveAll(categoryMapper.toListOfCategory(categoryDtos));

        return categoryMapper.toListOfCategoryDto(categories);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        if(Objects.isNull(categoryDto.getId())){
            //TODO handel the message
            throw new RuntimeException("ID must not be null");
        }
        Category category= categoryMapper.toCategory(categoryDto);
        //TODO will cause exception
        //TODO when save product save Category
        categoryRepo.save(category);
        return categoryDto;

    }

    @Override
    public List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtos) {
        categoryDtos.stream().forEach(categoryDto -> {
            if(Objects.isNull(categoryDto.getId())){
                //TODO handel the message
                throw new RuntimeException("ID must not be null");
            }
        });

        List<Category> categories= categoryRepo.saveAll(categoryMapper.toListOfCategory(categoryDtos));
        return categoryMapper.toListOfCategoryDto(categories);
    }

    @Override
    public boolean delete(Long id) {
        if(!categoryRepo.findById(id).isPresent()){
            //TODO handel the message
//            throw new RuntimeException("ID not found");
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Boolean> deleteList(List<Long> ids) {
        List<Boolean> isDeleted = new ArrayList<>();
        ids.stream().forEach(id->{
            if(!categoryRepo.findById(id).isPresent()){
                //TODO handel the message
//            throw new RuntimeException("ID not found");
                isDeleted.add(Boolean.FALSE);
            }else{
                isDeleted.add(Boolean.TRUE);
            }

        });

        categoryRepo.deleteAllById(ids);
        return isDeleted;
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if(!categoryRepo.findById(id).isPresent()){
            //TODO handel the message
            throw new RuntimeException("ID not found");
        }
        Category category =categoryRepo.findById(id).get();

        return categoryMapper.toCategoryDto(category);

    }

    @Override
    public List<CategoryDto> getAllCategories() {

        return categoryMapper.toListOfCategoryDto(categoryRepo.findAll());
    }
}
