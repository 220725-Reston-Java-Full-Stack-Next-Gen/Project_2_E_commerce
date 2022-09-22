package com.revature.services;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import com.revature.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;


    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAllProducts();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepo.findProductsByBrand(brand);
    }

    @Override
    public List<Product> getProductsByType(String type) {
        return productRepo.findProductByType(type);
    }

    @Override
    public Product getProductById(int productId) {
        return productRepo.getProductById(productId);
    }

    @Override
    public boolean deleteProductById(int productId) {
        return productRepo.deleteProductById(productId);
    }

    @Override
    public boolean updateProductById(String productName, double productPrice,
                                     String image_Link, String productDescription,
                                     int productRating, ProductBrand productBrand,
                                     ProductType productType, ProductColor productColor,
                                     int productId) {
        return productRepo.updateProductById(productName, productPrice,
                image_Link, productDescription, productRating, productBrand,
                productType, productColor, productId);
    }

    @Override
    public boolean insertProduct(String productName, double productPrice, String image_Link,
                                 String productDescription, int productRating, ProductBrand productBrand,
                                 ProductType productType, ProductColor productColor) {
        return productRepo.insertProduct(productName, productPrice,
                image_Link, productDescription, productRating, productBrand,
                productType, productColor);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }
}
