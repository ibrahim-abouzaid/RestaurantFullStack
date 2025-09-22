import {Product} from './product';

export class AddProduct extends Product{
  categoryId:number=null;
  constructor(product:Product, categoryId:number) {
    super();
    this.id = product.id;
    this.name = product.name;
    this.price = product.price;
    this.description = product.description;
    this.image = product.image;
    this.categoryId =categoryId
  }

}
