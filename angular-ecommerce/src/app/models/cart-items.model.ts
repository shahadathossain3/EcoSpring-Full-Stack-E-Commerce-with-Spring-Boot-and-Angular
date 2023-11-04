import {Product} from "./product.model";
import {Cart} from "./cart.model";

export class CartItems {
  id?:number;
  products?:Product;
  cart?:Cart;
  quantity?:number;
  totalItemsPrice?:number;
  totalItemsDiscount?:number;
  createDate?:Date;
  updateDate?: Date;


  constructor(id?: number, product?: Product, cart?: Cart, quantity?: number, totalItemsPrice?:number, totalItemsDiscount?: number, itemDiscount?:number, createDate?: Date, updateDate?: Date) {
    this.id = id;
    this.products = product;
    this.cart = cart;
    this.quantity = quantity;
    this.totalItemsPrice = totalItemsPrice;
    this.totalItemsDiscount=totalItemsDiscount;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }
}
