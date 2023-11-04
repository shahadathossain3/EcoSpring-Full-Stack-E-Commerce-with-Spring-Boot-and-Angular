import {Component, OnInit} from '@angular/core';
import {Category} from "../../models/category.model";
import {HttpClient} from "@angular/common/http";
import {CategoryService} from "../../services/category.service";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-category',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit{

  constructor(private categoryService:CategoryService, private router:Router) {
  }

  categories?:Category[]
  ngOnInit(): void {
    this.categoryList();
  }

  categoryList():void
  {
   this.categoryService.getAllCategory().subscribe(data=>{
     this.categories=data;
     console.log(this.categories);
   });
  }

  addCategory():void
  {
    this.router.navigate(['/add-category'])
  }

}
