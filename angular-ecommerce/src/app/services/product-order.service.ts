import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Observer} from "rxjs";
import {ProductOrder} from "../models/product-order.model";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ProductOrderService {

  constructor(private httpClient:HttpClient) { }

  getAllPendingProductOrder(): Observable<ProductOrder[]>
  {
    return this.httpClient.get<ProductOrder[]>(environment.rootUrl+'order/getAllPendingOrder')
  }

  saveOrderProduct(data: ProductOrder):Observable<any>
  {
    return this.httpClient.post<any>(environment.rootUrl+'order/addOrder',data,{observe:'response'});
  }

  updateOrderProductById(id: any,data:any)
  {
    return this.httpClient.put<any>(environment.rootUrl+'order/orderUpdateById/'+`${id}`,data);
  }

  deleteOrderProductById(id:any)
  {
    return this.httpClient.delete<any>(environment.rootUrl+'order/orderDeleteById/'+`${id}`);
  }

  getAllPendingDeliveryOrder():Observable<ProductOrder[]>
  {
    return this.httpClient.get<ProductOrder[]>(environment.rootUrl+'order/getAllPendingDeliveryOrder');
  }

  getPendingDeviveryCount():Observable<any>
  {
    return this.httpClient.get<any>(environment.rootUrl+'order/pendingDeliveryCount');
  }

}
