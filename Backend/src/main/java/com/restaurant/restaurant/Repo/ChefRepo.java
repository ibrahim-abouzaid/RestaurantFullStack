package com.restaurant.restaurant.Repo;

import com.restaurant.restaurant.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef,Long> {
}
