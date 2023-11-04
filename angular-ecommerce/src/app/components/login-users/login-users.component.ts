import {Component, OnInit} from '@angular/core';
import {Users} from "../../models/users.model";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginUsersService} from "../../services/login-users.service";
import {NgForm} from "@angular/forms";
import { getCookie } from 'typescript-cookie';

@Component({
  selector: 'app-login-users',
  templateUrl: './login-users.component.html',
  styleUrls: ['./login-users.component.css']
})
export class LoginUsersComponent implements OnInit{


  isLoading=false;
  users=new Users();
  authStatus:string="";
  message?:string;
  cartPara?:string;
  userData:any
  unauthorized?:string;

  constructor(private loginUsersService:LoginUsersService, private router:Router, private activatedRoute:ActivatedRoute) {
  }

  ngOnInit(): void {
       this.activatedRoute.queryParams.subscribe(params=>{
         this.cartPara=params['cart'];
       })
    }

  loginUser()
  {
    this.isLoading=true;
    this.loginUsersService.userLoginDetails(this.users).subscribe(data=>{
      console.log(data)
      if(data.status===200) {
        this.isLoading=false;
        window.sessionStorage.setItem("Authorization", data.headers.get('Authorization')!);
        this.users = <any>data.body;
        this.users.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails", JSON.stringify(this.users));
        // let xsrf = getCookie('XSRF-TOKEN')!;
        // window.sessionStorage.setItem("XSRF-TOKEN", xsrf);

        this.userData = sessionStorage.getItem("userdetails")?JSON.parse(sessionStorage.getItem("userdetails")!):'';


        const role = this.userData?.roles?.find((rl: { name: string; }) => rl.name === "ROLE_ADMIN")
        if (role && role.name === "ROLE_ADMIN") {
          this.router.navigate((['admin-template']))
        } else {
          if (this.cartPara === "cart") {
            this.router.navigate(['add-order-products'])
          } else {
            this.router.navigate(['list-products']);
          }
        }
      }

      if(data.status===401)
      {
        this.unauthorized="email or password did not match";
      }

    },
      error => {
      this.message=error.error.message;
      console.log(this.message)
        this.isLoading=false;
      })
  }
}
