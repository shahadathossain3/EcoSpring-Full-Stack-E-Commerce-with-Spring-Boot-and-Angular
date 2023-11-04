import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {Category} from "../../models/category.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidebars',
  templateUrl: './sidebars.component.html',
  styleUrls: ['./sidebars.component.css']
})
export class SidebarsComponent implements OnInit{


  categories?:Category[];
  constructor(private categoryService:CategoryService, private router:Router) {
  }
  ngOnInit(): void {
    this.categoryList();
  }


  categoryList()
  {
    this.categoryService.getAllCategory().subscribe(data=>{
      this.categories=data;
      console.log(this.categories);
    })
  }

  categoryProductSearch(categoryId:any)
  {
    console.log(categoryId)
    this.router.navigate(['category-product',categoryId])
  }

}
