package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.CatergotyDTO;
import com.example.springbootecommerce.model.Category;
import com.example.springbootecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("getAllCategory")
    public ResponseEntity<?> getAllCategory()
    {
        return ResponseEntity.ok().body(categoryService.categoryList());
    }

    @PostMapping("addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok().body(categoryService.addCategory(category));
    }

    @GetMapping("getCategoryWithProductList/{id}")
    public ResponseEntity<?> getCategoryByIdProductListDTO(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(categoryService.getCategoryByIdProductListDTO(id));
    }

    @GetMapping("getCategoryById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(categoryService.categoryFindByIdDTO(id));
    }


    @PutMapping("updateCategoryById/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Long id, @RequestBody CatergotyDTO catergotyDTO)
    {
        Category category=categoryService.categoryFindById(id);
        category.setName(catergotyDTO.getName());
        category.setDescription(catergotyDTO.getDescription());
        return ResponseEntity.ok().body(categoryService.updateCategoryById(category));
    }

    @DeleteMapping("deleteCategoryById/{id}")
    public void deleteCategoryById(@PathVariable Long id)
    {
        categoryService.deleteCategory(id);
    }
}
