import { Injectable } from '@angular/core';
import {ProductOrder} from '../model/ProductOrder';
import {BehaviorSubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  productOrders:ProductOrder[] =[];
  totalOrderSize:Subject<number> =new BehaviorSubject<number>(0);
  totalPrice:Subject<number> =new BehaviorSubject<number>(0);

  constructor() { }
  //case 1 : add for first time  quantity=1
  //case 2 : not for first time
  addToCart(product:ProductOrder){

    let isExists = false;
    let existProduct:ProductOrder=undefined;
    if(this.productOrders.length > 0){
      existProduct = this.productOrders.find((productOrder) => productOrder.id === product.id);

    }
    isExists = existProduct!=undefined;
    if(isExists){
      existProduct.quantity++;
    }else{
      this.productOrders.push(product);
    }
    console.log(this.productOrders);
    this.calculateTotals()
  }

  clearCart(){
    this.productOrders=[];
    this.totalOrderSize.next(0);
    this.totalPrice.next(0);
  }

  calculateTotals():void {

    let totalListedProduct:number=0;
    let totalOrderPrice:number=0;
    for(let order of this.productOrders){
      totalListedProduct +=order.quantity*order.price;
      totalOrderPrice+=order.quantity;
    }
    this.totalOrderSize.next(totalListedProduct) ;
    this.totalPrice.next(totalOrderPrice);
  }
  removeFromCart(product:ProductOrder){
    product.quantity--;
    if(product.quantity === 0){
      this.remove(product);
    }
    this.calculateTotals();

  }
  remove(product:ProductOrder){
   let index=this.productOrders.indexOf(product);
    this.productOrders.splice(index, 1);
  }



}
