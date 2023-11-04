import {Category} from "./category.model";


export class Product {
  id?: number;
  title?: string;
  price?:number;
  discount?:number;
  brand?:string;
  description?: string;
  image?: string;
  categories?: Category[];


  constructor(id?: number, title?: string, price?: number, description?: string, image?: string, categories?: Category[]) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.description = description;
    this.image = image;
    this.categories = categories;

  }
}
