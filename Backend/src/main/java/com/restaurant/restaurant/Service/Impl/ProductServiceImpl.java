package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.Mapper.ProductMapper;
import com.restaurant.restaurant.Repo.CategoryRepo;
import com.restaurant.restaurant.Repo.ProductRepo;
import com.restaurant.restaurant.Service.ProductService;
import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        if(Objects.nonNull(productDto.getId())){
            //TODO handel the message
            throw new RuntimeException("ID must be null");
        }
        Category category = categoryRepo.findById(productDto.getCategory_id()).get();

        Product product=  productMapper.toProduct(productDto);

        if(Objects.nonNull(category)){
            product.setCategory(category);
        }
        else {
            throw new RuntimeException("category.not.found");
        }

        //TODO will cause exception
        //TODO when save product save Category
        product= productRepo.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    @Override
    public List<ProductDto> saveList(List<ProductDto> productDtos) {
        productDtos.stream().forEach(productDto -> {
            if(Objects.nonNull(productDto.getId())){
                //if one or more object have id not null remove it and save the remaining
                productDtos.remove(productDto);
            }
        });
        List<Category> categories=new ArrayList<>();
        productDtos.stream().forEach(productDto -> {
            categories.add( categoryRepo.findById(productDto.getCategory_id()).get());
        });

        List<Product> products=productMapper.toListOfProduct(productDtos);
        for(int i=0;i<products.size();i++){
            products.get(i).setCategory(categories.get(i));
        }

        products=productRepo.saveAll(products);
        for(int i=0;i<products.size();i++){
            productDtos.get(i).setId(products.get(i).getId());
        }
        return productDtos;
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        if(Objects.isNull(productDto.getId())||!productRepo.existsById(productDto.getId())){
            //TODO handel the message
            throw new RuntimeException("ID must not be null");
        }
        Product product =productRepo.findById(productDto.getId()).get();

         product= productMapper.toProduct(productDto);
        return productMapper.toProductDto(productRepo.save(product));
    }

    @Override
    public List<ProductDto> updateList(List<ProductDto> productDtos) {
        productDtos.stream().forEach(productDto -> {
            if(Objects.isNull(productDto.getId())){
                //TODO handel the message
                productDtos.remove(productDto);
            }
        });
        List<Product> products = productRepo.findAllById(productDtos.stream().map(productDto->{
                    return productDto.getId();
        }).collect(Collectors.toList()));

        List<Category> categories= products.stream().map(product -> {
            return product.getCategory();
        }).collect(Collectors.toList());
        products =productMapper.toListOfProduct(productDtos);
        for(int i=0;i<products.size();i++){
            products.get(i).setCategory(categories.get(i));
        }
        products= productRepo.saveAll(products);

        for(int i=0;i<productDtos.size();i++){
            productDtos.get(i).setCategory_id(products.get(i).getCategory().getId());
        }

        return productDtos;
    }

    @Override
    public boolean delete(Long id) {
//        if(!productRepo.findById(id).isPresent()){
//            //TODO handel the message
//            throw new RuntimeException("ID not found");
//        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Boolean> deleteList(List<Long> ids) {

     List<Boolean> isDeleted = new ArrayList<>();
        ids.stream().forEach(id->{
            isDeleted.add(Boolean.TRUE);
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
        List<ProductDto> productDtos=productMapper.toListOfProductDto(products);
        for(int i=0;i<products.size();i++){
            productDtos.get(i).setCategory_id(products.get(i).getCategory().getId());
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryId(Long id) {
        List<Product> products= productRepo.findByCategory_Id(id);
        List<ProductDto> productDtos=productMapper.toListOfProductDto(products);

        productDtos.stream().forEach(productDto -> {
            productDto.setCategory_id(id);
        });


        return  productDtos;
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return   productMapper.toListOfProductDto(productRepo.findAll()) ;
    }


}
