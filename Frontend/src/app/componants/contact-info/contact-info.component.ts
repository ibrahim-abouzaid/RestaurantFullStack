import { Component, OnInit } from '@angular/core';
import {Product} from '../../../model/product';
import {ProductOrder} from '../../../model/ProductOrder';
import {CartService} from '../../../service/cart.service';

@Component({
  selector: 'app-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.css']
})
export class ContactInfoComponent implements OnInit {

  constructor(  ) { }

  ngOnInit(): void {
  }


}
