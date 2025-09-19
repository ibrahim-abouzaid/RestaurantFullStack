package com.restaurant.restaurant.Repo;


import com.restaurant.restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {


}
