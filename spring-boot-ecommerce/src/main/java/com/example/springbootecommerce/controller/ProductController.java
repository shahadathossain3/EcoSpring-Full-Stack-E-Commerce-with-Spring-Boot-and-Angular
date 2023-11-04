package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.CatergotyDTO;
import com.example.springbootecommerce.dto.ProductWithCategoryListDTO;
import com.example.springbootecommerce.model.Category;
import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.service.CategoryService;
import com.example.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("getAllProduct")
    public ResponseEntity<?> getAllProduct(@RequestParam("page") Integer page, @RequestParam("size") Integer size)
    {
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok().body(productService.productList(pageable));
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok().body(productService.addProduct(product));
    }

    @GetMapping("getProductWithCategoryList/{id}")
    public ResponseEntity<?> getProductByIdCategoryListDTO(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(productService.getProductByIdCategoryListDTO(id));
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<?> getSearchResult(@PathVariable String keyword)
    {
        return ResponseEntity.ok().body(productService.searchResult(keyword));
    }

    @PutMapping("updateProductById/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable Long id, @RequestBody ProductWithCategoryListDTO productWithCategoryListDTO)
    {
        Product product=productService.getProductById(id);
        List<Category> categories=new ArrayList<>();
        for(CatergotyDTO catergotyDTO:productWithCategoryListDTO.getCategories())
        {
            Category category=new Category();
            category.setId(catergotyDTO.getId());
            category.setName(catergotyDTO.getName());
            category.setDescription(catergotyDTO.getDescription());
            categories.add(category);
        }
        product.setTitle(productWithCategoryListDTO.getTitle());
        product.setPrice(productWithCategoryListDTO.getPrice());
        product.setDiscount(productWithCategoryListDTO.getDiscount());
        product.setBrand(productWithCategoryListDTO.getBrand());
        product.setDescription(productWithCategoryListDTO.getDescription());
        product.setImage(productWithCategoryListDTO.getImage());
        product.setCategories(categories);
        return ResponseEntity.ok().body(productService.productUpdateById(product));
    }

    @DeleteMapping("deleteProductById/{id}")
    public void deleteProductById(@PathVariable Long id)
    {
      productService.deleteProduct(id);
    }
}
