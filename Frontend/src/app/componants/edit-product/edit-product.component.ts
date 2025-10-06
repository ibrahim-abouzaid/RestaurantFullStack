import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Product} from '../../../model/product';
import {ProductService} from '../../../service/product.service';
import {CategoryService} from '../../../service/category.service';
import {Category} from '../../../model/category';
import {routes} from '../../app.module';
import {NotifyService} from '../../../service/notify.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  messageAr=' ';
  messageEn=' ';
  product: Product = {} as Product;
  categoryId: number=null;
  categoryList: Category[];
  categoryName: string="null";
  constructor(private productService:ProductService,private activatedRoute: ActivatedRoute,private categoryService: CategoryService,private router: Router
  ,private notifyService: NotifyService) { }


  ngOnInit(): void {

    this.getAllCategories();
    let hasId = this.activatedRoute.snapshot.paramMap.has('id');
    if (hasId) {
      this.getProductDetails(this.activatedRoute.snapshot.paramMap.get('id'));
    }

  }
  getProductDetails(id) {

    this.productService.getProductById(id).subscribe(
      response => {this.product.id = response.id
      this.product.name = response.name;
      this.product.description = response.description;
      this.product.price = response.price;
      this.product.image = response.image;
      this.categoryId = response.categoryId;
        this.getCategoryById(this.categoryId);

        }
    ),error => {
      console.log(error);
    }
  }
  getCategory(event:Event){

    this.categoryName = (<HTMLInputElement> event.target).value
  }
  getAllCategories() {

    this.categoryService.getCategories().subscribe(
      response => {
        this.categoryList = response;

      },error => {
        console.log(error);
      }
    )
  }
  getCategoryById(id) {

    this.categoryService.getCategoryById(id).subscribe(
      response => {
        this.categoryName=response.name
        console.log("category response name  is "+ response.name)
      }
    ),error => {
      console.log(error);
    }

  }
  saveProduct(id,name,description,price){
let selectedCategoryid=this.getCategoryIdByName(this.categoryName)
    this.productService.updateProduct(id,name,price,description,selectedCategoryid,this.product.image).subscribe(
      response =>{response
    this.clearForm();
        this.notifyService.triggerRefresh()
       this.router.navigateByUrl('/products')

      }
    ) ,error => {
      this.messageAr = error.error.bundleMessage.message_ar;
      this.messageEn = error.error.bundleMessage.message_en;
    }
  }
  getCategoryIdByName(name:string){
    return this.categoryList.find(c=>c.name == name).id;
  }
clearForm(){
 this.product= new Product();
this.categoryId=null;
this.categoryName=null;

}
}
