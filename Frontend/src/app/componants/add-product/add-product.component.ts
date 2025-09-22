import { Component, OnInit } from '@angular/core';
import {Category} from '../../../model/category';
import {CategoryService} from '../../../service/category.service';
import {Product} from '../../../model/product';
import {Router} from '@angular/router';
import {ProductService} from '../../../service/product.service';
import {AddProduct} from '../../../model/add-product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  categoryList: Category[];
  categoryName: string;
  isSaved: boolean=false;
  messageAr: string= '';
  messageEn: string = '';
  constructor(private categoryService:CategoryService,private router:Router,private productService:ProductService) { }

  ngOnInit(): void {
   this.getAllCategories();
  }
getAllCategories() {
    this.categoryService.getCategories().subscribe(
      response =>{
        this.categoryList = response;
      }
    )

}
  saveProduct(name,description,price){
    let categoryId=this.getCategoryId(this.categoryName);
    this.productService.saveProduct(name,price,description,categoryId,"dummy Image path").subscribe(
      response =>{
        this.isSaved = response
      },error=>{
        this.messageAr = error.error.bundleMessage.message_ar;
        this.messageEn = error.error.bundleMessage.message_en;
      }
    );
  }
  getCategory(event:Event){

    this.categoryName = (<HTMLInputElement> event.target).value
  }
  getCategoryId(categoryName:string):number{
    return this.categoryList.findIndex(cat => cat.name === categoryName);
  }
}
