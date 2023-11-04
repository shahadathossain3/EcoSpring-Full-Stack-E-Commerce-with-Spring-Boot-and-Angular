import {Product} from "./product.model";

export class OrderItems {
  id?:number;
  products?:Product;
  totalItemsPrice?:number;
  totalItemsDiscount?:number;
  quantity?:number;
  createDate?:Date;
  updateDate?:Date;


  constructor(id?: number, products?: Product, totalItemsPrice?: number, totalItemsDiscount?: number, quantity?: number, createDate?: Date, updateDate?: Date) {
    this.id = id;
    this.products = products;
    this.totalItemsPrice = totalItemsPrice;
    this.totalItemsDiscount = totalItemsDiscount;
    this.quantity = quantity;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }
}
