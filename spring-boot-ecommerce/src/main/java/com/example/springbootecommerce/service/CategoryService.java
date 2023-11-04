package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.CategoryWithProductListDTO;
import com.example.springbootecommerce.dto.CatergotyDTO;
import com.example.springbootecommerce.dto.ProductDTO;
import com.example.springbootecommerce.model.Category;
import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(@RequestBody Category category)
    {
        return categoryRepository.save(category);
    }


    public List<CatergotyDTO> categoryList()
    {
        List<Category> categories=categoryRepository.findAll();
        List<CatergotyDTO> catergotyDTOS=new ArrayList<>();
        for(Category category: categories)
        {
            CatergotyDTO catergotyDTO=new CatergotyDTO();
            catergotyDTO.setId(category.getId());
            catergotyDTO.setName(category.getName());
            catergotyDTO.setDescription(catergotyDTO.getDescription());
            catergotyDTOS.add(catergotyDTO);
        }
        return catergotyDTOS;
    }

    public Category categoryFindById(Long categoryId)
    {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public CatergotyDTO categoryFindByIdDTO(Long categoryId)
    {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        CatergotyDTO catergotyDTO=new CatergotyDTO();
        catergotyDTO.setId(category.getId());
        catergotyDTO.setName(category.getName());
        catergotyDTO.setDescription(category.getDescription());
        return catergotyDTO;
    }
    public CatergotyDTO updateCategoryById(Category requestCategory)
    {
        Category category=categoryRepository.save(requestCategory);
        CatergotyDTO catergotyDTO=new CatergotyDTO();
        catergotyDTO.setId(category.getId());
        catergotyDTO.setName(category.getName());
        catergotyDTO.setDescription(category.getDescription());
        return catergotyDTO;
    }

    public CategoryWithProductListDTO getCategoryByIdProductListDTO(Long id)
    {
        Category category=categoryRepository.findById(id).orElse(null);
        return categoryWithProductListDTO(category);
    }


    public CategoryWithProductListDTO categoryWithProductListDTO(Category category)
    {
        List<ProductDTO> productDTOS=new ArrayList<>();
        for (Product product:category.getProducts())
        {
            ProductDTO productDTO=new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setTitle(product.getTitle());
            productDTO.setDescription(product.getDescription());
            productDTO.setImage(product.getImage());
            productDTOS.add(productDTO);
        }
        CategoryWithProductListDTO categoryWithProductListDTO=new CategoryWithProductListDTO();
        categoryWithProductListDTO.setId(category.getId());
        categoryWithProductListDTO.setName(category.getName());
        categoryWithProductListDTO.setDescription(category.getDescription());
        categoryWithProductListDTO.setProducts(productDTOS);
        return categoryWithProductListDTO;
    }


    public void deleteCategory(Long id)
    {
        Category category=categoryRepository.findById(id).orElse(null);
        categoryRepository.delete(category);
    }



}
