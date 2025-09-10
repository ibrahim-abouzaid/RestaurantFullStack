package com.restaurant.restaurant.Service;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.controller.vm.ProductResponseVm;
import com.restaurant.restaurant.model.Product;

import java.util.List;

public interface ProductService {
    //basic crud
    ProductDto save(ProductDto productDto);
    List<ProductDto> saveList(List<ProductDto> productDtos);

    ProductDto update(ProductDto productDto);
   List<ProductDto>  updateList(List<ProductDto> productDtos);
    boolean delete(Long id);
    List<Boolean> deleteList(List<Long> ids);

    ProductDto getProduct(Long id);
    ProductResponseVm getAllProductsByKey(String key,int page,int size);
    ProductResponseVm getAllProductsByCategoryId(Long key,int page,int size);
    ProductResponseVm getAllProducts(int page,int size);


}
