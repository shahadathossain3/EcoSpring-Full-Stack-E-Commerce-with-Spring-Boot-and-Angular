import {Component, OnInit} from '@angular/core';
import {ProductOrderService} from "../../services/product-order.service";
import {ProductOrder} from "../../models/product-order.model";
import {Users} from "../../models/users.model";
import {OrderItems} from "../../models/order-items.model";
import {Cart} from "../../models/cart.model";
import {Roles} from "../../models/roles.model";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";

@Component({
  selector: 'app-products-order',
  templateUrl: './product-order.component.html',
  styleUrls: ['./product-order.component.css']
})
export class ProductOrderComponent implements OnInit{

  formData!:FormGroup;

  users?:Users;
  productOrder = new ProductOrder()
  images:string | undefined;
  orderitems:OrderItems[]=[];
  constructor(private productOrderService:ProductOrderService, private router:Router, private formBuilder: FormBuilder) {
  }


  ngOnInit(): void {
    this.formDataInit();
    this.loadLocalStroge();
  }


  formDataInit()
  {
    this.formData = this.formBuilder.group({
      firstName: [this.productOrder?.firstName, [Validators.required]],
      lastName: [this.productOrder?.lastName, [Validators.required]],
      mobile: [this.productOrder?.mobile, [Validators.required]],
      zipCode: [this.productOrder?.zipCode, [Validators.required]],
      address: [this.productOrder?.address, [Validators.required]],
      customerComment: [this.productOrder?.customerComment,'']
    });
  }

  loadLocalStroge()
  {

    const userData=sessionStorage.getItem("userdetails");
    const itemsData=localStorage.getItem("cartItems");
    if(itemsData) {
      const itnData = JSON.parse(itemsData);

      for(let data of itnData)
      {
        data.products.image=data.products.image.split(',')[1];
        // console.log(data);
        this.orderitems?.push(data)
      }

    }

    if(userData)
    {
      this.users=JSON.parse(userData);
    }
  }




  addProductOrder() {
    if (this.formData.valid) {
      this.productOrder.orderItems = this.orderitems;
      this.productOrder.users = this.users;
      this.productOrderService.saveOrderProduct(this.productOrder).subscribe(data => {
          if (data.status === 200) {
            localStorage.removeItem("cartItems")
            this.router.navigate(['/list-products'])

          }
        },
        error => console.log(error)
      )
    }
  }


}
