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

  getProducts(page ,size): Observable<any> {
    return this.http.get<Product[]>(this.baseUrl+'get-All-Product?page='+page+"&size="+size).pipe(
      map(
        response => response
      )
    );
  }
  getProductByCategoryId(id,page,size ) : Observable<any>{
    return this.http.get<Product[]>(this.baseUrl+'get-All-ByCategoryId/'+id + '?page='+page+"&size="+size).pipe(
      map(
        response => response
      )
    );
}
  searchByKey(key,page,size): Observable<any>{
    return this.http.get<Product[]>(this.baseUrl+'search?key='+key +'&page='+page+"&size="+size).pipe(
      map(
        response => response
      )
    )
  }
}
