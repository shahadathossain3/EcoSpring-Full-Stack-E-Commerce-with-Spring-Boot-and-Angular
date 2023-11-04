import {Component, OnInit} from '@angular/core';
import {CartItems} from "../../models/cart-items.model";
import {ProductService} from "../../services/product.service";
import {Users} from "../../models/users.model";
import {Router} from "@angular/router";
import {HeaderComponent} from "../header/header.component";
import {CartItemsService} from "../../services/cart-items.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{

  itemsStore?:CartItems[]=[];
  totalPrice=0;
  totalCartItems=0;
  totalDiscount=0;
  users?:Users;


  constructor(private productService:ProductService, private router:Router, private cartItemsService:CartItemsService) {
  }
  ngOnInit() {
    const userData=sessionStorage.getItem("userdetails");
    if(userData)
    {
      this.users=JSON.parse(userData);
    }
    this.loadlocalstoredata()
  }
  loadlocalstoredata()
  {
    const items=localStorage.getItem("cartItems");
    if(items) {
      this.itemsStore=JSON.parse(items);
      if(this.itemsStore)
      {
        this.totalPrice=0;
        this.totalCartItems=0;
        this.totalDiscount=0;
      for(let itmPrice of this.itemsStore) {
        if (itmPrice && itmPrice.totalItemsPrice && itmPrice.quantity && itmPrice.totalItemsDiscount) {

          this.totalPrice += itmPrice.totalItemsPrice
          this.totalCartItems += itmPrice.quantity;
          this.totalDiscount+= itmPrice.totalItemsDiscount;
          console.log(this.totalPrice)
        }
      }
      }
    }
  }

  removeItems(id: any): void {
    const productRemoveId = this.itemsStore?.findIndex(item => item.products?.id === id);

    if (productRemoveId !== -1) {
      this.itemsStore?.splice(productRemoveId as number, 1);
      localStorage.setItem("cartItems", JSON.stringify(this.itemsStore));
      this.loadlocalstoredata();
      this.cartItemsService.cartItemsQuantity();
    }
  }

  priceQuantityUpdate(cartItem: CartItems) {
    console.log(cartItem)
    const itemIndex = this.itemsStore?.findIndex(item => item.products?.id === cartItem.products?.id);

    if (cartItem && itemIndex !== undefined && itemIndex !== -1) {
      const productPrice = cartItem?.products;
      cartItem.totalItemsPrice = (productPrice?.price || 0) * (cartItem.quantity || 0);
      cartItem.totalItemsDiscount=(productPrice?.discount || 0) * (cartItem.quantity || 0)
      if(this.itemsStore) {
        this.itemsStore[itemIndex] = cartItem;
      }
      localStorage.setItem("cartItems", JSON.stringify(this.itemsStore));
      this.loadlocalstoredata();
    }
  }


  addOrderProduct()
  {
    if(this.users?.authStatus==='AUTH')
    {
      this.router.navigate(['add-order-products'])
    }
    else
    {
      this.router.navigate(['login-user'],{queryParams:{cart:'cart'}})
    }
  }
}
