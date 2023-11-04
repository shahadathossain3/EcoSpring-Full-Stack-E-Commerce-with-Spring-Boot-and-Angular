import { Component } from '@angular/core';
import {Users} from "../../models/users.model";

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent {

  users?:Users

}
