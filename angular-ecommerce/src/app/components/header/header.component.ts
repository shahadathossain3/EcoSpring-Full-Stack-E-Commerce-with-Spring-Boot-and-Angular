import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Users} from "../../models/users.model";
import {CartItemsService} from "../../services/cart-items.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  users=new Users();
  keyword:any;
  cartQuantity:any

  constructor(private router:Router, private cartItemsService:CartItemsService) {
  }

  ngOnInit(): void {

    this.cartItemsService.cartQuantity$.subscribe(data=>
    {
      this.cartQuantity=data;
    })

    this.cartItemsList()
    }

    cartItemsList()
    {
      if(sessionStorage.getItem('userdetails'))
      {
        this.users=JSON.parse(sessionStorage.getItem('userdetails')!);
      }
      this.cartQuantity=localStorage.getItem('cartItems')? JSON.parse(localStorage.getItem("cartItems")!).length:0;
    }

  search()
  {
    console.log(this.keyword)
    this.router.navigate(['search',this.keyword]);
  }

}
