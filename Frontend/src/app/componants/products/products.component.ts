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

  constructor(private productService: ProductService,private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
    ()=> this.loadUrl()
    )
  }

  loadUrl() : void {

    let hasId = this.activatedRoute.snapshot.paramMap.has('id');
    let hasKey = this.activatedRoute.snapshot.paramMap.has('key');

    if(hasId){
      let id = this.activatedRoute.snapshot.paramMap.get('id');
      this.getProductsByCategoryId(id);
    }
    else if(hasKey){
      let key = this.activatedRoute.snapshot.paramMap.get('key');
      this.search(key);
    }
    else {
      this.getProducts();
    }
  }

  getProducts(){
    this.productService.getProducts().subscribe(
      value => this.products = value
    );
  }
  getProductsByCategoryId( id ){
this.productService.getProductByCategoryId(id).subscribe(
  value => this.products = value
)
  }
  search(key:string ){
this.productService.searchByKey(key).subscribe(
  value => this.products = value
)
  }
}
