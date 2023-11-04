import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders, HttpErrorResponse
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Users} from "../models/users.model";
import {Router} from "@angular/router";

@Injectable()
export class AppInterceptorInterceptor implements HttpInterceptor {

  users = new Users();

  constructor(private router:Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let httpHeaders = new HttpHeaders();
    if(sessionStorage.getItem('userdetails'))
    {
      this.users=JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    if(this.users && this.users.email && this.users.password)
    {
      httpHeaders = httpHeaders.append('Authorization','Basic '+window.btoa(this.users.email +':'+ this.users.password));
    }
    else
    {
     let authorization = sessionStorage.getItem('Authorization');
     if(authorization)
     {
       httpHeaders = httpHeaders.append('Authorization', authorization);
     }
    }
    // let xsrf = sessionStorage.getItem('XSRF-TOKEN');
    // if(xsrf)
    // {
    //   httpHeaders=httpHeaders.append('X-XSRF-TOKEN', xsrf);
    // }
    httpHeaders=httpHeaders.append('X-Requested-With','XMLHttpRequest');
    const xhr=request.clone({
      headers:httpHeaders
    });
    return next.handle(xhr).pipe(tap(
      (err:any)=>{
        if(err instanceof HttpErrorResponse)
        {
          if(err.status!==401)
          {
            return;
          }
          this.router.navigate(['list-products']);
        }
      }
    ));
  }
}
