import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListProductComponent} from "./components/list-product/list-product.component";
import {AddCategoryComponent} from "./components/add-category/add-category.component";
import {ListCategoryComponent} from "./components/list-category/list-category.component";
import {AddProductComponent} from "./components/add-product/add-product.component";
import {DetailsProductComponent} from "./components/details-product/details-product.component";
import {CartComponent} from "./components/cart/cart.component";
import {ProductOrderComponent} from "./components/product-order/product-order.component";
import {ListProductOrderComponent} from "./components/list-product-order/list-product-order.component";
import {AddUsersComponent} from "./components/add-users/add-users.component";
import {LoginUsersComponent} from "./components/login-users/login-users.component";
import {DetailsUsersComponent} from "./components/details-users/details-users.component";
import {AuthRouterguardGuard} from "./routeguards/auth-routerguard.guard";
import {LogoutComponent} from "./components/logout/logout.component";
import {AdminTemplateComponent} from "./components/admin-template/admin-template.component";
import {ListUsersComponent} from "./components/list-users/list-users.component";
import {UsersOrderDetailsComponent} from "./components/users-order-details/users-order-details.component";

const routes: Routes = [
  {path:'', redirectTo:'/home', pathMatch: 'full'},
  {path:'home', component:ListProductComponent},
  {path: 'list-products', component: ListProductComponent},
  {path:'add-category', component:AddCategoryComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'list-category', component:ListCategoryComponent},
  {path: 'add-products', component:AddProductComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'details-products/:id', component: DetailsProductComponent},
  {path: 'category/:id', component: AddCategoryComponent},
  {path: 'products/:id', component: AddProductComponent},
  {path: 'cart', component: CartComponent},
  {path: 'pending-order-products', component: ListProductOrderComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'pending-delivery-order-products', component: ListProductOrderComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'add-order-products', component: ProductOrderComponent, canActivate:[AuthRouterguardGuard]},
  {path:'search/:keyword', component:ListProductComponent},
  {path: 'add-users', component:AddUsersComponent},
  {path: 'login-user', component:LoginUsersComponent},
  {path: 'user/user-details/:id', component: DetailsUsersComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'user/pending-order-details/:id', component: UsersOrderDetailsComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'user/history-order-details/:id', component: UsersOrderDetailsComponent, canActivate:[AuthRouterguardGuard]},
  {path: 'logout', component: LogoutComponent},
  {path: 'admin-template', component: AdminTemplateComponent},
  {path: 'category-product/:categoryId', component: ListProductComponent},
  {path: 'customer-list', component: ListUsersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
