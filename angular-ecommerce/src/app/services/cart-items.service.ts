import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartItemsService {

  cartItems:any;

  private cartQuantitySubject=new BehaviorSubject(0);

  cartQuantity$=this.cartQuantitySubject.asObservable();

  constructor() { }

  cartItemsQuantity()
  {
    const cartQuantity=localStorage.getItem('cartItems')? JSON.parse(localStorage.getItem("cartItems")!).length:0;

    this.cartQuantitySubject.next(cartQuantity);
  }
}
