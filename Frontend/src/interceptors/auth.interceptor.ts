import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from '../../service/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
//if user login , he has token
   if(this.authService.isUserLogin()){
     //add the header and fill it with the token then reassign it and return
     request=request.clone({
       setHeaders: {
         Authorization: "Bearer " + sessionStorage.getItem("token")
       }
     })
     //go ahead and continue
     return next.handle(request);
   }
    return next.handle(request);
    }

}
