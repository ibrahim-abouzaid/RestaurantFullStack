import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  messageAr: string= '';
  messageEn: string = '';

  constructor(private authService: AuthService,private router: Router) { }


  ngOnInit(): void {
  }

  login(username, password): void {
    if(!this.validateUser(username,password)){
      setTimeout(() => {
        this.messageEn = '';
        this.messageAr = '';
      }, 3000);
      return;
    }
    this.authService.login(username,password).subscribe(
      response => {
        sessionStorage.setItem("token", response.token);
        this.router.navigateByUrl("/products")//if created  route to the main page
      },error => {
        this.messageAr= error.error.bundleMessage.message_ar;
        this.messageEn =error.error.bundleMessage.message_en;
        setTimeout(() => {
          this.messageEn = '';
          this.messageAr = '';
        }, 3000);
      })

  }
 private validateUser(username, password) :boolean{

    if (!username) {
      this.messageAr = "اسم المستخدم مطلوب";
      this.messageEn = "Username is required";
      return false;
    }

    if (!password) {
      this.messageAr = "كلمة المرور مطلوبة";
      this.messageEn = "Password is required";
      return false;
    }

    return true;
  }

}
