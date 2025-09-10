import {Component, OnInit} from '@angular/core';
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

  constructor(private categoryService: CategoryService,private authService: AuthService) {

  }


  ngOnInit(): void {
       this.getCategories();
  }

  getCategories(){
    this.categoryService.getCategories().subscribe(
      value => this.categories = value
    );
  }
  isLoggedIn(): boolean{

    return this.authService.isUserLogin();
  }
}
