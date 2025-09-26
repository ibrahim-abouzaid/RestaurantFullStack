package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.Service.ProductService;
import com.restaurant.restaurant.controller.vm.ProductResponseVm;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "CRUD operations for products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Save a single product",
            description = "Creates and saves a new product in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product saved successfully",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data")
            }
    )
    @PostMapping("/save-Product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid ProductDto productDto){
        return ResponseEntity.ok().body(productService.save(productDto));
    }

    @Operation(summary = "Save multiple products")
    @PostMapping("/save-listOf-Products")
    public ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtos){
        return ResponseEntity.ok().body(productService.saveList(productDtos));
    }

    @Operation(summary = "Update a single product")
    @PutMapping("/update-Product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto){
        return ResponseEntity.ok().body(productService.update(productDto));
    }

    @Operation(summary = "Update multiple products")
    @PutMapping("/update-ListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProduct(@RequestBody @Valid List<ProductDto> productDtos){
        return ResponseEntity.ok().body(productService.updateList(productDtos));
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/delete-Product")
    public ResponseEntity<Boolean> deleteProduct(
            @Parameter(description = "Product ID to delete")
            @RequestParam @Valid Long id){
        return ResponseEntity.ok().body(productService.delete(id));
    }

    @Operation(summary = "Delete multiple products by IDs")
    @DeleteMapping("/delete-ListOf-Product")
    public ResponseEntity<List<Boolean>> deleteListOfProduct(
            @Parameter(description = "List of product IDs to delete")
            @RequestParam @Valid List<Long> ids){
        return ResponseEntity.ok().body(productService.deleteList(ids));
    }

    @Operation(summary = "Search products by keyword")
    @GetMapping("/search")
    public ResponseEntity<ProductResponseVm> getAllProductsContain(
            @Parameter(description = "Search keyword") @RequestParam @Valid String key,
            @Parameter(description = "Page number") @RequestParam int page,
            @Parameter(description = "Page size") @RequestParam int size){
        return ResponseEntity.ok().body(productService.getAllProductsByKey(key,page,size));
    }

    @Operation(summary = "Get all products by category ID")
    @GetMapping("/get-All-ByCategoryId/{id}")
    public ResponseEntity<ProductResponseVm> getAllProductsContain(
            @Parameter(description = "Page number") @RequestParam int page,
            @Parameter(description = "Page size") @RequestParam int size,
            @Parameter(description = "Category ID") @PathVariable @Valid Long id){
        return ResponseEntity.ok().body(productService.getAllProductsByCategoryId(id,page,size));
    }

    @Operation(summary = "Get all products (paginated)")
    @GetMapping("/get-All-Product")
    public ResponseEntity<ProductResponseVm> getAllProducts(
            @Parameter(description = "Page number") @RequestParam int page,
            @Parameter(description = "Page size") @RequestParam int size){
        return ResponseEntity.ok().body(productService.getAllProducts(page, size));
    }


    @Operation(summary = "Get product by ID", description = "Fetch a single product by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/get-ProductById")
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "Product ID") @RequestParam Long id ){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
}
