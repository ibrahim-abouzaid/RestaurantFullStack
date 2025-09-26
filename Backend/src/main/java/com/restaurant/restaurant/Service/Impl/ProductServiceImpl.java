package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.Mapper.ProductMapper;
import com.restaurant.restaurant.Repo.ProductRepo;
import com.restaurant.restaurant.Service.ProductService;
import com.restaurant.restaurant.controller.vm.ProductResponseVm;
import com.restaurant.restaurant.model.Product;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public ProductResponseVm getAllProductsByKey(String key,int page,int size) {
        try {


            Pageable pageable = getPageable(page, size);
            Page<Product> productPage = productRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(key, key, pageable);
            if (productPage.isEmpty()) {
                throw new SystemException("products.not.found");
            }
            return new ProductResponseVm(
                    productPage.getContent()
                            .stream()
                            .map(productMapper::toProductDto).toList(),
                    productPage.getTotalElements()
            );
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public ProductResponseVm getAllProductsByCategoryId(Long id,int page,int size) {
        try {
        Pageable pageable = getPageable(page, size);
        Page<Product> productPage = productRepo.findByCategoryId(id, pageable);
        if (productPage.isEmpty()) {
            throw new SystemException("products.not.found");
        }
            return new ProductResponseVm(
                    productPage.getContent()
                            .stream()
                            .map(productMapper::toProductDto).toList(),
                    productPage.getTotalElements()
            );
    }   catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


    //@Cacheable(value = "products" ,key = "'allProduct'")
    public ProductResponseVm getAllProducts(int page, int size) {
        try {
            Pageable pageable = getPageable(page, size);
            Page<Product> productPage = productRepo.findAll(pageable);
            if (productPage.isEmpty()) {
                throw new SystemException("products.not.found");
            }
            return new ProductResponseVm(
                    productPage.getContent()
                            .stream()
                            .map(productMapper::toProductDto).toList(),
                    productPage.getTotalElements()
            );
        } catch (SystemException e){
                throw new RuntimeException(e.getMessage());
            }

        }

    @Override
    public ProductDto getProductById(Long id) {
        if(productRepo.findById(id).isPresent()){
            return productMapper.toProductDto(productRepo.findById(id).get());
        }
        else{
            throw new RuntimeException("product.not.found");

        }
    }

    @Override
    public List<ProductDto> getProductByIds(List<Long> ids) {

        return productMapper.toListOfProductDto( productRepo.findAllById(ids));
    }


    private Pageable getPageable (int page ,int size){
        try {
            if (page < 1) {
            throw new SystemException("error.min.one.page");
        }
       return PageRequest.of(page - 1, size);
   } catch (SystemException e) {
        throw new RuntimeException(e.getMessage());
    }
   }

}
