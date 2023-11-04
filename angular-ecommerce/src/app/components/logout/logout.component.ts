import {Component, OnInit} from '@angular/core';
import {Users} from "../../models/users.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit{

  users=new Users();

  constructor(private router:Router) {
  }

  ngOnInit(): void {
    window.sessionStorage.removeItem("userdetails");
    window.sessionStorage.removeItem("XSRF-TOKEN");
    window.sessionStorage.removeItem("Authorization");
    this.router.navigate(['list-products'])
  }



}
