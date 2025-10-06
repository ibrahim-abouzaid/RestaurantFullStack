import {Component, OnInit} from '@angular/core';
import {Router, Routes} from '@angular/router';
import {AuthService} from '../../../service/auth.service';
import {NotifyService} from '../../../service/notify.service';
import {Notification} from '../../../model/notification';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


constructor(private router: Router,private authService: AuthService) {
}
  ngOnInit(): void {


  }
  search(key):void {
this.router.navigateByUrl("/products/"+ key);
  }

  isUserAuthenticated(): boolean {
  return this.authService.isUserLogin();
  }

  logout(): void {

    this.authService.logout();
    this.router.navigate(['/login']);
  }
  isUserAdmin(){
  return this.authService.isUserAdmin();
  }
}
