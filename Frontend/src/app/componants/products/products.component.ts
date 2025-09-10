import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../../model/product';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent  implements OnInit{

  products: Product[] = [];
  pageNumber : number = 1;
  pageSize: number = 20;
  totalProductsSize: number = 0;

  messageAr: string= '';
  messageEn: string = '';

  constructor(private productService: ProductService,private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
    ()=> this.loadUrl(this.pageNumber)
    )
  }

  loadUrl(pageNumber) : void {
    this.messageAr= '';
    this.messageEn= '';
    this.pageNumber=1;
    let hasId = this.activatedRoute.snapshot.paramMap.has('id');
    let hasKey = this.activatedRoute.snapshot.paramMap.has('key');

    if(hasId){
      let id = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(id,pageNumber);
    }
    else if(hasKey){
      let key = this.activatedRoute.snapshot.paramMap.get('key');
      this.search(key,pageNumber);
    }
    else {
      this.getProducts(pageNumber);
    }
  }

  getProducts(page){
    this.productService.getProducts(page,this.pageSize).subscribe(
      response => { this.products = response.products
                        this.totalProductsSize =response.totalProducts
      },error => {
          this.messageAr = error.error.bundleMessage.message_ar;
          this.messageEn = error.error.bundleMessage.message_en;
          this.products=[]

        }

    );
  }
  getProductsByCategoryId( id,page ){

this.productService.getProductByCategoryId(id,page,this.pageSize).subscribe(
  response => {
    this.products = response.products
    this.totalProductsSize =response.totalProducts
  },error => {
      this.messageAr = error.error.bundleMessage.message_ar;
      this.messageEn = error.error.bundleMessage.message_en;
      this.products=[]

    }

)
  }
  search(key,page ){
this.productService.searchByKey(key,page,this.pageSize).subscribe(
  response => {
    this.products = response.products
    this.totalProductsSize =response.totalProducts
  },error => {
      debugger
      this.messageAr = error.error.bundleMessage.message_ar;
      this.messageEn = error.error.bundleMessage.message_en;
      this.products=[]

    }

)
  }
  pagaintion(){
  this.loadUrl(this.pageNumber);
  }
  changePageSize(event: Event){
   this.pageSize= +(<HTMLInputElement> event.target).value
    this.pagaintion()
  }
}
