import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl= ' http://localhost:8080/api/products/'
  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl+'get-All-Product').pipe(
      map(
        response => response
      )
    );
  }
  getProductByCategoryId(id: number ) : Observable<Product[]>{
    return this.http.get<Product[]>(this.baseUrl+'get-All-ByCategoryId/'+id).pipe(
      map(
        response => response
      )
    );
}
  searchByKey(key: string): Observable<Product[]>{
    return this.http.get<Product[]>(this.baseUrl+'search/'+key).pipe(
      map(
        response => response
      )
    )
  }
}
