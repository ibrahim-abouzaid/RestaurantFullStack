package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {
    //crud op
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save-Product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid ProductDto productDto){
        return ResponseEntity.ok().body (productService.save(productDto));

    }
    @PostMapping("/save-listOf-Products")
    public ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtos){
        return ResponseEntity.ok().body (productService.saveList(productDtos));

    }
    @PutMapping("/update-Product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto){
        return ResponseEntity.ok().body (productService.update(productDto));

    }
    @PutMapping("/update-ListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProduct(@RequestBody @Valid List<ProductDto> productDtos){
        return ResponseEntity.ok().body (productService.updateList(productDtos));

    }
    @DeleteMapping("/delete-Product")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam @Valid Long id){
        return ResponseEntity.ok().body (productService.delete(id));

    }
    @DeleteMapping("/delete-ListOf-Product")
    public ResponseEntity<List<Boolean>> deleteListOfProduct(@RequestParam @Valid List<Long> ids){
        return ResponseEntity.ok().body (productService.deleteList(ids));

    }
    @GetMapping("/search/{key}")
    public ResponseEntity<List<ProductDto>> getAllProductsContain(@PathVariable @Valid String  key){
        return ResponseEntity.ok().body (productService.getAllProductsByKey(key));

    }

    @GetMapping("/get-All-ByCategoryId/{id}")
    public ResponseEntity<List<ProductDto>> getAllProductsContain(@PathVariable @Valid Long  id){
        return ResponseEntity.ok().body (productService.getAllProductsByCategoryId(id));

    }
    @GetMapping("/get-All-Product")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body (productService.getAllProducts());

    }


}
