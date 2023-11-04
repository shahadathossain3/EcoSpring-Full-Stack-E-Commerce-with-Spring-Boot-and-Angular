import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Product} from "../models/product.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient:HttpClient) { }

  getAllProduct(page:any, size:any): Observable<any>
  {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())

    return this.httpClient.get<any>(environment.rootUrl+'product/getAllProduct', {params})
  }

  saveProduct(data:Product):Observable<Product>
  {
    return this.httpClient.post<Product>(environment.rootUrl+'product/addProduct',data);
  }

  getProductById(id:number): Observable<Product>
  {
    return this.httpClient.get<Product>(environment.rootUrl+'product/getProductWithCategoryList/'+`${id}`);
  }

  updateProductById(id:number, data:Product):Observable<Product>
  {
    return this.httpClient.put<Product>(environment.rootUrl+'product/updateProductById/'+`${id}`, data);
  }

  deleteProductById(id:any):Observable<any>
  {
    return this.httpClient.delete(environment.rootUrl+'product/deleteProductById/'+`${id}`);
  }

  searchProduct(keyword:any)
  {
    return this.httpClient.get<any>(environment.rootUrl+'product/search/'+`${keyword}`);
  }
}
