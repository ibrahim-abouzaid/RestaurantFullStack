package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.CategoryDto;
import com.restaurant.restaurant.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")

public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }




    @PostMapping("/save-Category")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        return ResponseEntity.ok().body (categoryService.save(categoryDto));

    }
    @PutMapping("/update-Category")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto){
        return ResponseEntity.ok().body (categoryService.update(categoryDto));

    }

    @PostMapping("/save-ListOf-Categories")
    public ResponseEntity<List<CategoryDto>> saveListOfCategory(@RequestBody @Valid List<CategoryDto> categoryDtos){
        return ResponseEntity.ok().body (categoryService.saveListOfCategories(categoryDtos));

    }

    @PutMapping("/update-ListOf-Categories")
    public ResponseEntity<List<CategoryDto>> updateListOfCategories(@RequestBody @Valid List<CategoryDto> categoryDtos){
        return ResponseEntity.ok().body (categoryService.updateListOfCategories(categoryDtos));

    }
    @DeleteMapping("/delete-categoryByid")
    public ResponseEntity<Boolean> deleteCategory(@RequestParam @Valid Long id){
        return ResponseEntity.ok().body (categoryService.delete(id));

    }

    @DeleteMapping("/delete-categoryByListOfid")

    public ResponseEntity<List<Boolean>> deleteCategory(@RequestParam @Valid List<Long> ids){
        return ResponseEntity.ok().body (categoryService.deleteList(ids));

    }

    @GetMapping("/get-All-Categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok().body (categoryService.getAllCategories());

    }
    @GetMapping("/get-CategoryById")
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam Long id){
        return ResponseEntity.ok().body (categoryService.getCategory(id));

    }
}
