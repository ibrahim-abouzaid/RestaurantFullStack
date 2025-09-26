package com.restaurant.restaurant.Repo;


import com.restaurant.restaurant.model.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    List<Orders>  findByUserId(Long UserId);
}
