package com.revature.services;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByType(String type);

    Product getProductById(int productId);

    boolean deleteProductById(int product_id);

    boolean updateProductById(String productName, double productPrice, String image_Link,
                                     String productDescription, int productRating,
                                     ProductBrand productBrand, ProductType productType,
                                     ProductColor productColor, int productId);

    boolean insertProduct(String productName, double productPrice,String image_Link,
                          String productDescription, int productRating,ProductBrand productBrand,
                          ProductType productType, ProductColor productColor);

    Product addProduct(Product product);

    List<Product> searchProduct(String term);
}
