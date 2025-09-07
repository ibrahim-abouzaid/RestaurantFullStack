import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  messageAr: string= '';
  messageEn: string = '';

  constructor(private authService: AuthService,private router: Router) { }

  ngOnInit(): void {
  }

  signup(username,password,confirmpassword){
    if(!this.validateUser(username,password,confirmpassword)){
      setTimeout(() => {
        this.messageEn = '';
        this.messageAr = '';
      }, 3000);
      return;
    }
    this.authService.createUser(username,password).subscribe(
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
      }
    )



  }

  validateUser(username: string, password: string, confirmpassword: string) :boolean{

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

    if (!confirmpassword) {
      this.messageAr = "تأكيد كلمة المرور مطلوب";
      this.messageEn = "Confirm password is required";
      return false;
    }

    if (password !== confirmpassword) {
      this.messageAr = "كلمتا المرور غير متطابقتين";
      this.messageEn = "Passwords do not match";
      return false;
    }
    return true;
  }


}
