import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class UserService {
private baseUrl = "http://localhost:8080/user/";
  constructor(private http: HttpClient) { }

  changeUserPassword(currentPassword,newPassword,confirmNewPassword){
    return this.http.put(this.baseUrl +"Change-password",{currentPassword,newPassword,confirmNewPassword}).pipe(
      map(
        response => response
      )
    )

  }
}
