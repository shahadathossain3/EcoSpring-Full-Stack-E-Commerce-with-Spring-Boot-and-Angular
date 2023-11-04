import { Injectable } from '@angular/core';
import {Route} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Users} from "../models/users.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }


  addUsers(data?: Users):Observable<Users>
  {
    return this.httpClient.post<Users>(environment.rootUrl+'user/register',data);
  }

  getUserByIdOrderListHistory(id:any):Observable<any>
  {
    return this.httpClient.get<any>(environment.rootUrl+'user/getUserOrderListHistory/'+`${id}`);
  }

  getUserByIdOrderListPending(id:any):Observable<any>
  {
    return this.httpClient.get<any>(environment.rootUrl+'user/getUserOrderListPending/'+`${id}`);
  }
}
