import { Component, OnInit } from '@angular/core';
import {CartService} from '../../../service/cart.service';
import {RequestOrderService} from '../../../service/request-order.service';
import {Orders} from '../../../model/orders';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

 orders:Orders[];
 totalPrice:number;
 totalSize:number;

  constructor(private orderService: RequestOrderService) { }

  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders(): void {
    this.orderService.getAllUserOrders().subscribe(
      response=>{
        this.orders = response.orders;
        this.totalPrice = response.totalPrice;
        this.totalSize = response.totalSize;
      }
    )
  }
}
