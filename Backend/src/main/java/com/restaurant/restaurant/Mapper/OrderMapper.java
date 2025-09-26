package com.restaurant.restaurant.Mapper;

import com.restaurant.restaurant.DTO.OrderDto;
import com.restaurant.restaurant.model.Orders;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Orders orders);

    Orders toOrders(OrderDto orderDto);
    List<OrderDto> toListOfOrdersDto(List<Orders> orders);

    List<Orders> toListOfOrders(List<OrderDto> orderDtos);
}
