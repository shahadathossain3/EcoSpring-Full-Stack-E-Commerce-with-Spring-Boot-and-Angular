import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "../models/category.model";
import {Observable, Observer} from "rxjs";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient:HttpClient) { }

  getAllCategory():Observable<Category[]>
  {
    return this.httpClient.get<Category[]>(environment.rootUrl+'category/getAllCategory');
  }

  saveCategory(data: Category):Observable<Category>
  {
    return this.httpClient.post<Category>(environment.rootUrl+'category/addCategory',data);
  }

  getCategoryById(id:number):Observable<Category>
  {
    return this.httpClient.get<Category>(environment.rootUrl+'category/getCategoryById/'+`${id}`);
  }

  updateCategoryById(id:number,data:Category):Observable<Category>
  {
    return this.httpClient.put<Category>(environment.rootUrl+'category/updateCategoryById/'+`${id}`,data);
  }

  deleteCategoryById(id:number):Observable<any>
  {
    return this.httpClient.delete(environment.rootUrl+'category/deleteCategoryById/'+`${id}`);
  }

  getCategoryByIdWithProductList(id?:number):Observable<any>
  {
    return this.httpClient.get<any>(environment.rootUrl+'category/getCategoryWithProductList/'+`${id}`);
  }


}
