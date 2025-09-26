package com.restaurant.restaurant.controller.vm;

import com.restaurant.restaurant.DTO.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderHistoryVm {
    private List<OrderDto> orders;
    private Double totalSize;
    private Double totalPrice;

}
