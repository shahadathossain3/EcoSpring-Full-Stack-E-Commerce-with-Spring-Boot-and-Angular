import {Product} from "./product.model";


export class Category {
  id?: number;
  name?: string;
  description?: string;
  product?: Product[]=[];


  constructor(id?: number, name?: string, description?: string, product?: Product[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.product = product;
  }
}
