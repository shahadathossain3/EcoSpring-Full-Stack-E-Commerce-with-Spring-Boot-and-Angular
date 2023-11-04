import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {Product} from "../../models/product.model";
import {ActivatedRoute} from "@angular/router";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-list-products',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit{
  currentPage = 1;
  totalElements = 0;
  productsList?:Product[];
  searchKeyword?:string;
  categoryId?: number;
  constructor(private productService:ProductService, private activatedRoute:ActivatedRoute, private categoryService:CategoryService) {
  }
  ngOnInit(): void {

    this.initLandingData()

  }


  initLandingData()
  {

    this.activatedRoute.params.subscribe(params=>{
      this.searchKeyword= params['keyword'];
      this.categoryId= params['categoryId'];

      if(this.searchKeyword)
      {
        this.searchProduct();
      }

      else if(this.categoryId)
      {
        this.getCategoryProduct();
      }

      else
      {
        this.productList();
      }
    })
  }


  getCategoryProduct()
  {
    this.productsList=[];
    this.categoryService.getCategoryByIdWithProductList(this.categoryId).subscribe(data=>{
      this.productsList=data.products.map((data: { image: string; })=>({
        ...data,
        image:'data:image/png;base64,'+data.image
      }));
      console.log(this.productsList);
    })
  }

  searchProduct()
  {
    this.productsList=[];
    this.productService.searchProduct(this.searchKeyword).subscribe(data=>{
      this.productsList=data.map((data: { image: string; })=>({
        ...data,
        image:'data:image/png;base64,'+data.image
      }));
      console.log(data);
    })
  }

  productList()
  {
    this.productService.getAllProduct(this.currentPage-1, 8)
      .subscribe(
        dataList=>{
          this.productsList=dataList.map((data: { image: string; })=>({
            ...data,
            image:'data:image/png;base64,'+data.image
          }));
          console.log(dataList);
        },
        error=>console.log(error)
      )
  }

  pageChanged(page: number) {
    this.currentPage = page;
    this.productList();
  }

}
