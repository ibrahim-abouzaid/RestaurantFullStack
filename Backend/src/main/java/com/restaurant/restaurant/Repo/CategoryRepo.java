package com.restaurant.restaurant.Repo;

import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByProducts (Product  product);

}
