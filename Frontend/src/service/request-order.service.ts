import { Injectable } from '@angular/core';
import {ProductOrder} from '../model/ProductOrder';
import {Observable, Observer} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';
import {map} from 'rxjs/operators';
import {Orders} from '../model/orders';

@Injectable({
  providedIn: 'root'
})
export class RequestOrderService {

  private baseUrl: string ="http://localhost:8080/order/";
  constructor(private http: HttpClient) { }

  createOrder(productsIds,totalPrice,totalNumber): Observable<any>{
    return this.http.post<ProductOrder>(this.baseUrl +"create-order", {productsIds,totalPrice,totalNumber}).pipe(
      map(
        response => response
      )
    )

  }
  getAllUserOrders(){
return this.http.get<any>(this.baseUrl + "myOrder-history").pipe(
  map(
    response => response
  )
);
  }
}
