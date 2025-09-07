package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.Mapper.ProductMapper;
import com.restaurant.restaurant.Repo.ProductRepo;
import com.restaurant.restaurant.Service.ProductService;
import com.restaurant.restaurant.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo , ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        if (productDto.getId() != null || productDto.getName().isBlank()) {
            throw new RuntimeException("Product name must not be empty");
        }
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productRepo.save(product));

    }

    @Override
    public List<ProductDto> saveList(List<ProductDto> productDtos) {
        productDtos.forEach(productDto -> {
            if (productDto.getId() == null || productDto.getName().isBlank()) {
                throw new RuntimeException("Product name must not be empty");
            }
        });

        List<Product> products = productMapper.toListOfProduct(productDtos);
        return productMapper.toListOfProductDto(productRepo.saveAll(products));


    }

    @Override
    public ProductDto update(ProductDto productDto) {
        if (productDto.getId() == null || !productRepo.existsById(productDto.getId())) {
            throw new RuntimeException("Product with ID " + productDto.getId() + " not found");
        }
        Product product = productMapper.toProduct(productDto);

        return productMapper.toProductDto(productRepo.save(product));
    }

    @Override
    public List<ProductDto> updateList(List<ProductDto> productDtos) {
        productDtos.forEach(productDto -> {
            if (productDto.getId() == null || !productRepo.existsById(productDto.getId())) {
                throw new RuntimeException("Product with ID " + productDto.getId() + " not found");
            }
        });
        List<Product> products = productMapper.toListOfProduct(productDtos);
        return productMapper.toListOfProductDto(productRepo.saveAll(products));


    }

    @Override
    public boolean delete(Long id) {
        if(!productRepo.existsById(id)){
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Boolean> deleteList(List<Long> ids) {

     List<Boolean> isDeleted = new ArrayList<>();
        ids.forEach(id->{
            if(!productRepo.existsById(id)){
                isDeleted.add(Boolean.FALSE);
            }
            else{
                isDeleted.add(Boolean.TRUE);
            }

        });
        productRepo.deleteAllById(ids);
        return isDeleted;
    }

    @Override
    public ProductDto getProduct(Long id) {
        return productMapper.toProductDto(productRepo.findById(id).get());
    }

    @Override
    public List<ProductDto> getAllProductsByKey(String key) {
       List<Product> products= productRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(key,key);
        return productMapper.toListOfProductDto(products);
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryId(Long id) {
        return   productMapper.toListOfProductDto(productRepo.findByCategoryId(id));
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return   productMapper.toListOfProductDto(productRepo.findAll()) ;
    }


}
