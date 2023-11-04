import {Component, OnInit} from '@angular/core';
import {Category} from "../../models/category.model";
import {CategoryService} from "../../services/category.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit{

  category=new Category();
  categoryId: number | undefined

  constructor(private categoryService:CategoryService, private router:Router, private activatedRoute:ActivatedRoute) {
  }

  ngOnInit(): void {
        this.categoryId=this.activatedRoute.snapshot.params['id'];
        if(this.categoryId)
        {
          this.getCategoryById(this.categoryId);
        }
    }

  saveCategory():void{
    const data={...this.category}
    this.categoryService.saveCategory(data).subscribe(data=>{
        console.log(data);
        // this.saveCategory()
        this.router.navigate(['/list-category'])
      },
      error => console.log(error));
  }

    getCategoryById(id:number)
    {
      this.categoryService.getCategoryById(id).subscribe(data=>{
        this.category=data;
      })
    }

    updateCategoryById():void
    {
      if(this.categoryId) {
        const data = {...this.category}
        this.categoryService.updateCategoryById(this.categoryId, data).subscribe(data => {
          this.router.navigate(['/list-category'])
        },
          error => console.log(error))
      }
    }

    deleteCategory():void
    {
     if (this.categoryId)
     {
       this.categoryService.deleteCategoryById(this.categoryId).subscribe(data=>{
         console.log(data);
         this.router.navigate(['list-category'])
       },error => console.log(error))
     }
    }


}
