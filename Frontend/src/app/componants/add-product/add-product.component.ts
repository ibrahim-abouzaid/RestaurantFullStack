import { Component, OnInit } from '@angular/core';
import {Category} from '../../../model/category';
import {CategoryService} from '../../../service/category.service';
import {Product} from '../../../model/product';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../service/product.service';
import {AddProduct} from '../../../model/add-product';
import {NotifyService} from '../../../service/notify.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  id:number=null;
  product:Product;
  categoryList: Category[];
  categoryName: string;
  isSaved: boolean=false;
  messageAr: string= '';
  messageEn: string = '';
  constructor(private categoryService:CategoryService,private router:Router,private productService:ProductService,
                private activatedRoute:ActivatedRoute,private notifyService:NotifyService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
   this.getAllCategories();

  }
getAllCategories() {
    this.categoryService.getCategories().subscribe(
      response =>{
        this.categoryList = response;
        if (this.categoryList.length > 0) {
          debugger
          this.categoryName = this.categoryList[1].name; // 👈 set default
        }
      }

    )



}


  saveProduct(name,description,price){
   let selectedCategory= this.categoryList.find(category => category.name === this.categoryName);
    //let categoryId=this.getCategoryId(this.categoryName);
    this.productService.saveProduct(name.value,price.value,description.value,selectedCategory.id,"dummy Image path").subscribe(
      response =>{
        if(response != null){
          this.isSaved = true;
          this.messageEn = 'Product saved successfully!';
          this.messageAr = 'تم حفظ المنتج بنجاح';
          this.notifyService.triggerRefresh();
          name.value=''
            price.value=null
              description.value=''
        }


        setTimeout(() => {
          this.isSaved = false;
          this.messageEn = '';
          this.messageAr = '';




        }, 3000);
      },error=>{
        this.isSaved = false;
        this.messageAr = error.error.bundleMessage.message_ar;
        this.messageEn = error.error.bundleMessage.message_en;
        setTimeout(() => {
          this.messageAr = null;
          this.messageEn = null;
        }, 3000);
      }
    );



  }
  getCategory(event:Event){

    this.categoryName = (<HTMLInputElement> event.target).value
  }
  getCategoryId(categoryName:string):number{
    return this.categoryList.findIndex(cat => cat.name === categoryName);
  }
  // updateProduct(name,description,price,id){
  //   let categoryId=this.getCategoryId(this.categoryName);
  // return this.productService.updateProduct(id,name,price,description,categoryId,"dummy Image path").subscribe(
  //   response =>{
  //     this.isSaved = response
  //   },error=>{
  //     this.messageAr = error.error.bundleMessage.message_ar;
  //     this.messageEn = error.error.bundleMessage.message_en;
  //   }
  // )
  // }

}
