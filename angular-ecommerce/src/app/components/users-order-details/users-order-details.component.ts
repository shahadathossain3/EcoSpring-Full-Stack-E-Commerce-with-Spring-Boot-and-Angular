import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Users} from "../../models/users.model";
import {Product} from "../../models/product.model";

@Component({
  selector: 'app-users-order-details',
  templateUrl: './users-order-details.component.html',
  styleUrls: ['./users-order-details.component.css']
})
export class UsersOrderDetailsComponent implements OnInit{

  producstList?:Product[];

  users?:Users;

  constructor(private userService:UserService, private router:Router, private activatedRoute:ActivatedRoute) {
  }


  ngOnInit(): void {
    this.users=sessionStorage.getItem("userdetails")?JSON.parse(sessionStorage.getItem("userdetails")!):'';
    if (this.activatedRoute.pathFromRoot.some(route => route.routeConfig?.path === 'user/pending-order-details/:id')) {
      this.getUserAllPendingOrder()
    }

    if (this.activatedRoute.pathFromRoot.some(route => route.routeConfig?.path === 'user/history-order-details/:id')) {
      this.getAllOrderHistory()
    }


  }

  getUserAllPendingOrder()
  {
    this.userService.getUserByIdOrderListPending(this.users?.id).subscribe(data=>{
      this.producstList=data
      console.log(this.producstList)
    })
  }

  getAllOrderHistory()
  {
    this.userService.getUserByIdOrderListHistory(this.users?.id).subscribe(data=>{
      this.producstList=data
      console.log(this.producstList)
    })
  }

}
