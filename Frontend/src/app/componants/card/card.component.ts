import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, Routes} from '@angular/router';
import {ProductsComponent} from "../products/products.component";
import {CardDetailsComponent} from "../card-details/card-details.component";
import {ContactInfoComponent} from "../contact-info/contact-info.component";
import {ChefsComponent} from "../chefs/chefs.component";
import {CartService} from '../../../service/cart.service';
import {ProductOrder} from '../../../model/ProductOrder';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent  implements OnInit {
 productOrders:ProductOrder[] =[];
 productSize:number=0;
 productPrice:number=0;

 constructor(private cartService: CartService) {}

  ngOnInit(): void {
        this.getTotal();
    }

  getTotal(){
   this.cartService.totalPrice.subscribe(
     value=>{
       this.productPrice=value;
     }
   )
    this.cartService.totalOrderSize.subscribe(
      value=>{
        this.productSize=value;
      }
    )
  }

}
