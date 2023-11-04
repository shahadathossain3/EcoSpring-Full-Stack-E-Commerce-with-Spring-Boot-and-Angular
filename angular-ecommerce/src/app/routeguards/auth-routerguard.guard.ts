import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {Users} from "../models/users.model";

@Injectable({
  providedIn: 'root'
})
export class AuthRouterguardGuard implements CanActivate {
  users=new Users();

  constructor(private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(sessionStorage.getItem('userdetails'))
    {
      this.users=JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    if(!this.users)
    {
      this.router.navigate(['/login']);
    }
    return this.users?true:false;
  }

}
