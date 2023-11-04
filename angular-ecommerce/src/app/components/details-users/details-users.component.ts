import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-details-users',
  templateUrl: './details-users.component.html',
  styleUrls: ['./details-users.component.css']
})
export class DetailsUsersComponent implements OnInit{

  userId?:number;

  userData:any;
  pendingData:any;
  historySize=0;
  pendingSize=0;

  constructor(private userService:UserService, private activatedRoute:ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getUserById();
  }

  getUserById()
  {
    this.userId= this.activatedRoute.snapshot.params['id'];

    this.userService.getUserByIdOrderListHistory(this.userId).subscribe(data=>{
      this.userData=data;
      console.log(this.userData);
      this.historySize=data.length;
    })

    this.userService.getUserByIdOrderListPending(this.userId).subscribe(data=>
    {
      this.pendingData=data;
      this.pendingSize=data.length;
    })
  }

}
