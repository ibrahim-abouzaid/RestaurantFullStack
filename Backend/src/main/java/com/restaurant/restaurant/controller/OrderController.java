package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.Service.OrderService;
import com.restaurant.restaurant.controller.vm.RequestOrderVm;
import com.restaurant.restaurant.controller.vm.ResponseOrderVm;
import com.restaurant.restaurant.controller.vm.UserOrderHistoryVm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService   orderService;
@Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<ResponseOrderVm> createOrder(@RequestBody @Valid RequestOrderVm requestOrderVm){
    return  ResponseEntity.ok( ).body(
            orderService.requestedOrder(requestOrderVm)
    );
    }

    @GetMapping("/myOrder-history")
    public ResponseEntity<UserOrderHistoryVm> getMyOrderHistory( ){
        return  ResponseEntity.ok( ).body(
                orderService.getAllUserOrders()
        );
}
}
