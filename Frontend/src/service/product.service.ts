import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productUrl = 'http://localhost:8080/get-All-Product';
  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl).pipe(
      map(
        response => response
      )
    );
  }
}
