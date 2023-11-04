import {Component, OnInit} from '@angular/core';
import {Category} from "../../models/category.model";

import {Product} from "../../models/product.model";
import {CategoryService} from "../../services/category.service";
import {Alert} from "../../models/alert.model";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Cart} from "../../models/cart.model";
import {Roles} from "../../models/roles.model";
import {Users} from "../../models/users.model";



@Component({
  selector: 'app-add-products',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit{
  // list-users?:Users;

  carts: Cart = { id: 1, name: "Product 1"};
  roles: Roles[] = [{ id: 1, name: "ROLE_ADMIN" }];

// Create initial list-users data
  user: Users = new Users(
    1,
    "John",
    "Doe",
    "john@example.com",
    "password123",
    "1234567890",
    "avatar.jpg",
    this.carts,
    this.roles
  );





  categoriesList:Category[]=[];
  categories:Category[]=[];
  category=new Category();

  base64Image:string | null = null;
  imageFile:File | undefined;
  imageBase64: string | undefined;
  image:string|undefined

  productId:number|undefined;





  product=new Product();

  constructor(private categoryService:CategoryService, private productService:ProductService, private router:Router, private activatedRoute:ActivatedRoute) {
  }


  ngOnInit(): void {
    this.productId=this.activatedRoute.snapshot.params['id']
    if(this.user.roles) {
      for (let role of this.user?.roles) {
        if (this.productId && role.name==="ROLE_ADMIN") {
          this.getProductById();
        }
      }
    }
    this.categoryList()

  }

  getProductById()
  {
    if(this.productId) {
      this.productService.getProductById(this.productId).subscribe(data=>{
        this.product=data;
        console.log(this.product)
      })
    }
  }



  OnChaneCategory():void
{
  this.categories.push(this.category)

  console.log(this.categories);
}

  categoryList():void
  {
    this.categoryService.getAllCategory().subscribe(data=>{
        this.categoriesList=data;
        console.log(this.categoriesList)
    });
  }


  onImageChange(event:any):void
  {
    this.imageFile = event.target.files[0];
    this.getBase4Image();
  }

 getBase4Image()
  {
    if (this.imageFile) {
      const reader = new FileReader();
      reader.onload = (event: any) => {
        this.imageBase64 = event.target.result;
        console.log(this.imageBase64)
        this.image=this.imageBase64
        this.product.image = this.imageBase64?.split(',')[1];
        // this.products.image="data:image/jpeg;base64,"+this.products.
      };
      reader.readAsDataURL(this.imageFile);
    }
  }

  saveProduct():void
  {
    this.product.categories=this.categories
    const data={...this.product}
    console.log(data)
    this.productService.saveProduct(data).subscribe(data=>{
    console.log(data);
      this.router.navigate(['/list-products']);
    },
      error => console.log(error));
  }

  updateProduct(id:any):void
  {
    this.product.categories=this.categories
    const data={...this.product}
    this.productService.updateProductById(id, data).subscribe(data=>{
      console.log(data);
      this.router.navigate(['list-products']);
    })
  }
}
