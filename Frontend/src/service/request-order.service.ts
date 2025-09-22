import { Injectable } from '@angular/core';
import {ProductOrder} from '../model/ProductOrder';
import {Observable, Observer} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environments/environment';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RequestOrderService {

  private baseUrl: string ="http://localhost:8080/order/create-order";
  constructor(private http: HttpClient) { }

  createOrder(productsIds,totalPrice,totalNumber): Observable<any>{
    return this.http.post<ProductOrder>(this.baseUrl, {productsIds,totalPrice,totalNumber}).pipe(
      map(
        response => response
      )
    )

  }
}
