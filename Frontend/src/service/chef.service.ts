import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Chef} from '../model/chef';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChefService {
  chefUrl = 'http://localhost:8080/get-All-Chefs';
  constructor(private http: HttpClient) {
  }
  getChefs(): Observable<Chef[]>{
    return this.http.get<Chef[]>(this.chefUrl).pipe(
      map(response => response)
    );
  }
}
