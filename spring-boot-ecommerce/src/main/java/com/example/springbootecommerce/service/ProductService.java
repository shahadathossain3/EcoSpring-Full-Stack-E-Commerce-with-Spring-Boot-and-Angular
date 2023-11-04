package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.CatergotyDTO;
import com.example.springbootecommerce.dto.ProductWithCategoryListDTO;
import com.example.springbootecommerce.model.Category;
import com.example.springbootecommerce.model.Product;
import com.example.springbootecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<ProductWithCategoryListDTO> searchResult(String keyword)
    {
        List<Product> products=productRepository.findByTitleContainingIgnoreCase(keyword);
        List<ProductWithCategoryListDTO> productWithCategoryListDTOS=new ArrayList<>();
        for(Product product : products)
        {
            productWithCategoryListDTOS.add(productWithCategoryListDTO(product));
        }
        return productWithCategoryListDTOS;
    }

    public List<ProductWithCategoryListDTO> productList(Pageable pageable)
    {
        Page<Product> productPage;
        productPage=productRepository.findAll(pageable);
//        List<Product> products=productRepository.findAll();
        List<ProductWithCategoryListDTO> productWithCategoryListDTOS=new ArrayList<>();
        for(Product product:productPage)
        {
            productWithCategoryListDTOS.add(productWithCategoryListDTO(product));
        }
        return productWithCategoryListDTOS;
    }

    public Product getProductById(Long id)
    {
        Product product=productRepository.findById(id).orElse(null);
        return product;
    }

    public ProductWithCategoryListDTO getProductByIdCategoryListDTO(Long id)
    {

        Product product=productRepository.findById(id).orElse(null);
        return productWithCategoryListDTO(product);
    }

    public ProductWithCategoryListDTO productUpdateById(Product requestProduct)
    {
        Product product=productRepository.save(requestProduct);

        return productWithCategoryListDTO(product);
    }

    public void deleteProduct(Long productId)
    {
     Product product=productRepository.findById(productId).orElse(null);
        productRepository.delete(product);
    }

    public ProductWithCategoryListDTO productWithCategoryListDTO(Product product)
    {
        List<CatergotyDTO> catergotyDTOS=new ArrayList<>();
        for(Category category:product.getCategories())
        {
            CatergotyDTO catergotyDTO=new CatergotyDTO();
            catergotyDTO.setId(category.getId());
            catergotyDTO.setName(category.getName());
            catergotyDTO.setDescription(category.getDescription());
            catergotyDTOS.add(catergotyDTO);
        }
        ProductWithCategoryListDTO productWithCategoryListDTO=new ProductWithCategoryListDTO();
        productWithCategoryListDTO.setId(product.getId());
        productWithCategoryListDTO.setTitle(product.getTitle());
        productWithCategoryListDTO.setPrice(product.getPrice());
        productWithCategoryListDTO.setDiscount(product.getDiscount());
        productWithCategoryListDTO.setBrand(product.getBrand());
        productWithCategoryListDTO.setDescription(product.getDescription());
        productWithCategoryListDTO.setImage(product.getImage());
        productWithCategoryListDTO.setCategories(catergotyDTOS);
        return productWithCategoryListDTO;
    }
}
