package com.restaurant.restaurant.Repo;

import com.restaurant.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByCategory_Id(Long id);

}
