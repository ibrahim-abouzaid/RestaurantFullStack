package com.restaurant.restaurant.Service;

import com.restaurant.restaurant.controller.vm.RequestOrderVm;
import com.restaurant.restaurant.controller.vm.ResponseOrderVm;
import com.restaurant.restaurant.controller.vm.UserOrderHistoryVm;

public interface OrderService {
    ResponseOrderVm requestedOrder(RequestOrderVm requestOrderVm);
    UserOrderHistoryVm getAllUserOrders();

}
