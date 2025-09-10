import { Component } from '@angular/core';
import {Router, Routes} from '@angular/router';
import {AuthService} from '../../../service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

constructor(private router: Router,private authService: AuthService) {
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
}
