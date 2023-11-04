import {Cart} from "./cart.model";
import {Roles} from "./roles.model";

export class Users {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
  mobile?: string;
  image?: string;
  carts?: Cart;
  roles?: Roles[];
  authStatus?:string;
  statusMsg?:string;


  constructor(id?: number, firstName?: string, lastName?: string, email?: string, password?: string, mobile?: string, image?: string, carts?: Cart, roles?: Roles[], authStatus?:string, statusMsg?:string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.mobile = mobile;
    this.image = image;
    this.carts = carts;
    this.roles = roles;
    this.statusMsg = statusMsg;
    this.authStatus = authStatus;
  }
}
