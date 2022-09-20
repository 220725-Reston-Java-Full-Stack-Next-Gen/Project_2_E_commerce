package com.revature.controllers;


import java.util.List;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")

public class ProductController {
//todo: use client messages, and change urls
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products-by-brand")
    public List<Product> getProductsByBrand(@RequestParam String brand){
        return productService.getProductsByBrand(brand);
    }
    @GetMapping("/products-by-type")
    public List<Product> getProductsByType(@RequestParam String type){
        return productService.getProductsByType(type);
    }
    @GetMapping("/product-by-id")
    public Product getProductByID(@RequestParam int id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/delete-product")
        public boolean deleteProductById(@RequestParam int id){
        return productService.deleteProductById(id);
    }

    @PutMapping("/update-product")
    public boolean updateProductById(@RequestBody String productName,
                                     @RequestBody double productPrice,
                                     @RequestBody String imageLink,
                                     @RequestBody String productDescription,
                                     @RequestBody int productRating,
                                     @RequestBody ProductBrand productBrand,
                                     @RequestBody ProductType productType,
                                     @RequestBody ProductColor productColor,
                                     @RequestBody int productId){
        return productService.updateProductById(productName, productPrice,
                imageLink, productDescription, productRating, productBrand,
                productType, productColor, productId);
    }
    @PostMapping("add-product")
    public boolean addProduct(@RequestBody String productName,
                              @RequestBody double productPrice,
                              @RequestBody String imageLink,
                              @RequestBody String productDescription,
                              @RequestBody int productRating,
                              @RequestBody ProductBrand productBrand,
                              @RequestBody ProductType productType,
                              @RequestBody ProductColor productColor){
        return productService.insertProduct(productName, productPrice,
                imageLink, productDescription, productRating, productBrand,
                productType, productColor);
    }


}
