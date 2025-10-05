import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category} from '../../../model/category';
import {AuthService} from '../../../service/auth.service';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit{

  categories: Category[] = [];

  constructor(private categoryService: CategoryService,private authService: AuthService,private cdr: ChangeDetectorRef) {

  }


  ngOnInit(): void {

     let islogin =this.isLoggedIn()
    if(islogin){
      this.getCategories();
    }

  }

  getCategories(){
    this.categoryService.getCategories().subscribe(
      value => {this.categories = value
    this.cdr.detectChanges();
      }
    );
  }
  isLoggedIn(): boolean{

    return this.authService.isUserLogin();
  }
}
