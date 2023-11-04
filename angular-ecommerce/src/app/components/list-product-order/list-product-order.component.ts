import {Component, OnInit} from '@angular/core';
import {ProductOrder} from "../../models/product-order.model";
import {HttpClient} from "@angular/common/http";
import {ProductOrderService} from "../../services/product-order.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-list-product-order',
  templateUrl: './list-product-order.component.html',
  styleUrls: ['./list-product-order.component.css']
})
export class ListProductOrderComponent implements OnInit{

  isPending=false;
  isDelivery=false;

  pendingProductOrderList?:ProductOrder[];

  constructor(private productOrderService:ProductOrderService, private activatedRoute:ActivatedRoute) {
  }


  ngOnInit(): void {
    if (this.activatedRoute.pathFromRoot.some(route => route.routeConfig?.path === 'pending-order-products')) {
      this.getAllPendingOrder();
    }

    if (this.activatedRoute.pathFromRoot.some(route => route.routeConfig?.path === 'pending-delivery-order-products')) {
      this.getAllPendingDeliveryList();
    }

  }


  getAllPendingOrder()
  {
    this.isPending=true;
    this.productOrderService.getAllPendingProductOrder().subscribe(data=>{
      this.pendingProductOrderList=data;
      console.log(this.pendingProductOrderList)
    })
  }

  updateProductOrder(id:any, data:any)
  {
    this.productOrderService.updateOrderProductById(id, data).subscribe(data=>{
      console.log(data);
      this.getAllPendingOrder();
    })

  }

  deleteOrder(id:any)
  {
    this.productOrderService.deleteOrderProductById(id).subscribe(data=>{
      console.log(data);
      this.getAllPendingOrder();
    })
  }

  getAllPendingDeliveryList()
  {
    this.isDelivery=true;
    this.productOrderService.getAllPendingDeliveryOrder().subscribe(data=>{
      this.pendingProductOrderList=data;
    })
  }

}
