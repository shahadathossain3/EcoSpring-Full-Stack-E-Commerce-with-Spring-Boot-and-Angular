import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddProductComponent } from './components/add-product/add-product.component';
import { ListProductComponent } from './components/list-product/list-product.component';
import { AddCategoryComponent } from './components/add-category/add-category.component';
import { ListCategoryComponent } from './components/list-category/list-category.component';
import { SidebarsComponent } from './components/sidebars/sidebars.component';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { AlertComponent } from './components/alert/alert.component';
import { DetailsProductComponent } from './components/details-product/details-product.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { AddUsersComponent } from './components/add-users/add-users.component';
import { DetailsUsersComponent } from './components/details-users/details-users.component';
import { CartItemsComponent } from './components/cart-items/cart-items.component';
import { CartComponent } from './components/cart/cart.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';
import { ProductOrderComponent } from './components/product-order/product-order.component';
import { ListProductOrderComponent } from './components/list-product-order/list-product-order.component';
import { BannerComponent } from './components/banner/banner.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginUsersComponent } from './components/login-users/login-users.component';
import {AppInterceptorInterceptor} from "./interceptors/app-interceptor.interceptor";
import {AuthRouterguardGuard} from "./routeguards/auth-routerguard.guard";
import { LogoutComponent } from './components/logout/logout.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { AdminTemplateSidebarComponent } from './components/admin-template-sidebar/admin-template-sidebar.component';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersOrderDetailsComponent } from './components/users-order-details/users-order-details.component';
import { FooterComponent } from './components/footer/footer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    AppComponent,
    AddProductComponent,
    ListProductComponent,
    AddCategoryComponent,
    ListCategoryComponent,
    SidebarsComponent,
    AlertComponent,
    DetailsProductComponent,
    ListUsersComponent,
    AddUsersComponent,
    DetailsUsersComponent,
    CartItemsComponent,
    CartComponent,
    OrderItemsComponent,
    ProductOrderComponent,
    ListProductOrderComponent,
    BannerComponent,
    HeaderComponent,
    LoginUsersComponent,
    LogoutComponent,
    AdminTemplateComponent,
    AdminTemplateSidebarComponent,
    UsersOrderDetailsComponent,
    FooterComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    NgbAlertModule,
    HttpClientModule,
    NgbDropdownModule,
    FontAwesomeModule,
    // HttpClientXsrfModule.withOptions({
    //   cookieName: 'XSRF-TOKEN',
    //   headerName: 'X-XSRF-TOKEN',
    // }),
  ],
  providers: [
    {
      provide : HTTP_INTERCEPTORS,
      useClass : AppInterceptorInterceptor,
      multi : true
    },AuthRouterguardGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
