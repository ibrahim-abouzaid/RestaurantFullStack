import { Component } from '@angular/core';
import {ProductOrder} from '../../../model/ProductOrder';
import {ProductService} from '../../../service/product.service';
import {CartService} from '../../../service/cart.service';
import {RequestOrderService} from '../../../service/request-order.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent {

  productOrders: ProductOrder[]=[];
  totalPrice: number=0;
  totalProductsSize: number=0;

  constructor(private cartService: CartService,private requestOrderService: RequestOrderService, private router: Router) { }

  ngOnInit() {
    this.productOrders = this.cartService.productOrders;
    this.cartService.totalPrice.subscribe(
      value => this.totalPrice = value
    )
    this.cartService.totalOrderSize.subscribe(
      value => this.totalProductsSize = value
    )
  }

  addOneProduct(productOrder: ProductOrder){
      this.cartService.addToCart(productOrder);

  }
  removeOneProduct(productOrder: ProductOrder){
    this.cartService.removeFromCart(productOrder);
  }
  deleteProduct(productOrder: ProductOrder){
    this.cartService.remove(productOrder);
    this.cartService.calculateTotals();
  }
  placeOrder(){
    const productIds=this.cartService.productOrders.map(item=>item.id);
    this.requestOrderService.createOrder(productIds,this.totalProductsSize,this.totalPrice).subscribe(
      response=>{
        this.cartService.clearCart();
        debugger
        this.router.navigateByUrl('/order-code/' + response.code);
      }
    );


  }
}
