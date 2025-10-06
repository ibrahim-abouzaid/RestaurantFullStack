import {Product} from './product';

export class Orders{
  id:number;
  products:Product[];
  totalSize:number;
  totalPrice:number;
  code:string;
}
