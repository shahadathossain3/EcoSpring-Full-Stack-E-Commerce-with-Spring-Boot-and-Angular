import {Users} from "./users.model";
import {OrderItems} from "./order-items.model";

export class ProductOrder {
  id?:number;
  users?:Users;
  status?:string;
  orderItems?:OrderItems[];
  mobile?:string;
  firstName?:string;
  lastName?:string;
  address?:string;
  zipCode?:string;
  customerComment?:string;
  adminComment?:string;
  approveStatus?:boolean;
  deliveryStatus?:boolean;
  totalPrice?:number;
  totalItems?:number;


  constructor(id?: number, users?: Users, status?: string, orderItems?: OrderItems[], mobile?: string, firstName?: string, lastName?: string, address?: string, zipCode?: string, customerComment?: string, adminComment?: string, approveStatus?: boolean, deliveryStatus?: boolean, totalPrice?: number, totalItems?: number) {
    this.id = id;
    this.users = users;
    this.status = status;
    this.orderItems = orderItems;
    this.mobile = mobile;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.zipCode = zipCode;
    this.customerComment = customerComment;
    this.adminComment = adminComment;
    this.approveStatus = approveStatus;
    this.deliveryStatus = deliveryStatus;
    this.totalPrice = totalPrice;
    this.totalItems = totalItems;
  }
}
