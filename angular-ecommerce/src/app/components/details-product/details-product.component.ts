import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product.model";
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from "../../services/product.service";
import {Users} from "../../models/users.model";
import {Roles} from "../../models/roles.model";
import {Cart} from "../../models/cart.model";
import {CartItems} from "../../models/cart-items.model";
import {CartItemsService} from "../../services/cart-items.service";


@Component({
  selector: 'app-details-products',
  templateUrl: './details-product.component.html',
  styleUrls: ['./details-product.component.css']
})
export class DetailsProductComponent implements OnInit {

  // list-users?:Users;

  // carts: Cart = { id: 1, name: "Product 1"};
  // roles: Roles[] = [{ id: 1, name: "ROLE_ADMIN" }];



// Create initial list-users data
//   users: Users = new Users(
//     1,
//     "John",
//     "Doe",
//     "john@example.com",
//     "password123",
//     "1234567890",
//     "avatar.jpg",
//     this.carts,
//     this.roles
//   );
  items: any[] = [
    { name: 'Item 1' },
    { name: 'Item 2' },
    { name: 'Item 3' },
    // ... more items
  ];


  users?:Users;

  removeItem(index: number): void {
    this.items.splice(index, 1);
  }



  image:any;
  constructor(private activatedRoute:ActivatedRoute, private productService: ProductService, private router:Router, private cartItemsService:CartItemsService) {
  }
  products?:Product;
  cartItemsStore?:[]
  cartItemsArray:CartItems[]=[];
  productId?:number;


  ngOnInit(): void {
    this.getProductById();
    this.loadStoreCartItems();
    this.checkCartItems();
    // if(sessionStorage.getItem("userdetails"))
    // {
      this.users=sessionStorage.getItem("userdetails")?JSON.parse(sessionStorage.getItem("userdetails")!):'';
    // }

  }

  getProductById()
  {
    this.productId = this.activatedRoute.snapshot.params['id'];
    if(this.productId) {
      this.productService.getProductById(this.productId).subscribe(data => {
        this.image = 'data:image/png;base64,' + data.image
        this.products = data;
        this.products.image = this.image;
        console.log(this.products);

      });
    }

  }

  roleAccessButton() {
    if (this.users?.roles) {
      for (let role of this.users.roles) {
        console.log(role.name)
        // role.name="ROLE_USER";
        if (role.name === "ROLE_USER") {
          return true;
        }
      }
    }
    return false;
  }


  deleteProduct(id:any):void
  {
    this.productService.deleteProductById(id).subscribe(data=>{
      this.router.navigate(['list-products']);
    })
  }


  loadStoreCartItems()
  {
    const localStore=localStorage.getItem("cartItems");
    if(localStore)
    {
      this.cartItemsArray=JSON.parse(localStore);
    }
  }


  addToCart(product: Product) {
    let cartItem = new CartItems();
    cartItem.cart = this.users?.carts;
    cartItem.products = product;
    cartItem.totalItemsPrice=product.price;
    cartItem.quantity=1;
    cartItem.totalItemsDiscount=product.discount
    this.cartItemsArray.push(cartItem);
    console.log(this.cartItemsArray);
    localStorage.setItem("cartItems", JSON.stringify(this.cartItemsArray));
    this.checkCartItems();
    this.cartItemsService.cartItemsQuantity();

  }

  checkCartItems()
  {
   for(let item of this.cartItemsArray)
   {
     if(item.products?.id==this.productId)
     {
       return true;
     }
   }
    return false
  }
}
