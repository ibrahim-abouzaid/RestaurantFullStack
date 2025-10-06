import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../service/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  messageEn='';
  messageAr='';
  isChanged = false;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }
  changePassword(currentPassword, newPassword,confirmNewPassword): void {
    if(confirmNewPassword.value !== newPassword.value) {
      this.messageEn='password not match';
      this.messageAr='كلمة السر غير متطابقة';

    }else{
    this.userService.changeUserPassword(currentPassword.value, newPassword.value,confirmNewPassword.value).subscribe(
      response => {
        if(response){
          this.messageEn='password changed successfully';
          this.messageAr='تم تغير كلمة المرور بنجاح'
          this.isChanged = true;
        }
      },error => {
        this.messageAr = error.error.bundleMessage.message_ar;
        this.messageEn = error.error.bundleMessage.message_en;
        this.isChanged = false;

      }
    )
    }
    currentPassword.value=null
    newPassword.value=null
    confirmNewPassword.value=null
    setTimeout(() => {
      this.messageEn = '';
      this.messageAr = '';
      this.isChanged = false;
    }, 3000);
  }
}
