package com.restaurant.restaurant.Service.Impl;

import com.restaurant.restaurant.DTO.ProductDto;
import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Mapper.ProductMapper;
import com.restaurant.restaurant.Mapper.security.UserMapper;
import com.restaurant.restaurant.Repo.OrderRepo;
import com.restaurant.restaurant.Service.OrderService;
import com.restaurant.restaurant.Service.ProductService;
import com.restaurant.restaurant.controller.vm.RequestOrderVm;
import com.restaurant.restaurant.controller.vm.ResponseOrderVm;
import com.restaurant.restaurant.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepo  orderRepo;

    private ProductService productService;
    private ProductMapper productMapper;
    private UserMapper userMapper;
@Autowired
    public OrderServiceImpl(OrderRepo orderRepo, ProductService productService, ProductMapper productMapper,UserMapper userMapper) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.productMapper = productMapper;
        this.userMapper=userMapper;
    }

    @Override
    public ResponseOrderVm requestedOrder(RequestOrderVm requestOrderVm) {
        List<ProductDto> productDtos=productService.getProductByIds(requestOrderVm.getProductsIds());
        UserDto userDto= (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Orders order =new Orders();
        order.setTotalNumber(requestOrderVm.getTotalNumber());
        order.setTotalPrice(requestOrderVm.getTotalPrice());
        order.setProducts(productMapper.toListOfProduct(productDtos));
        order.setUser(userMapper.toUser(userDto));

        Orders orderSaved = orderRepo.save(order);
        Long id = orderSaved.getId();
        String code = "RES-" + id;
        orderSaved.setCode(code);
        orderSaved = orderRepo.save(order);


        return new ResponseOrderVm(orderSaved.getCode(), orderSaved.getTotalPrice(), orderSaved.getTotalNumber());
    }
}
