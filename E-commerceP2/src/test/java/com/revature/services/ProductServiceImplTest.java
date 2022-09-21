package com.revature.services;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import com.revature.repos.ProductRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductServiceImplTest {
    @Autowired
    private ProductRepo productRepo;
    private ProductServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProductServiceImpl();
    }

    @AfterEach
    void tearDown() {
        productRepo.deleteAll();
    }
    //String productName, double productPrice, String image_Link, String productDescription,
//                   int productRating, ProductBrand productBrand, ProductType productType, ProductColor productColor
//    @Test
//    void getAllProducts() {
//        ProductBrand brand = new ProductBrand("Fenty");
//        ProductType type = new ProductType("lipStick");
//        ProductColor color = new ProductColor("ffffff", "white");
//
//        ProductBrand brand2 = new ProductBrand("Glossier");
//        ProductType type2 = new ProductType("lipStick");
//        ProductColor color2 = new ProductColor("000000", "black");
//
//        Product testMakeup = new Product(
//                "Fenty",
//                300.00,
//                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
//                "This is makeup",
//                5,
//                brand,
//                type,
//                color);
//
//        Product testMakeup2 = new Product(
//                "Glossier Lipstick",
//                200.00,
//                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
//                "This is makeup",
//                5,
//                brand2,
//                type2,
//                color2);
//
//        productRepo.saveAll(Arrays.asList(testMakeup,testMakeup2));
//        List<Product> products = underTest.getAllProducts();
//
//        assertNotEquals(1,products.size());
//    }

    @Test
    void getProductsByBrand() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }

    @Test
    void getProductsByType() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }

    @Test
    void getProductById() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }

    @Test
    void deleteProductById() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }

    @Test
    void updateProductById() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }

    @Test
    void insertProduct() {
        ProductBrand brand = new ProductBrand("Fenty");
        ProductType type = new ProductType("lipStick");
        ProductColor color = new ProductColor("ffffff", "white");
        Product testMakeup = new Product(
                "Fenty",
                300.00,
                "https://cdn.shopify.com/s/files/1/0341/3458/9485/products/57587.jpg?v=1652309037",
                "This is makeup",
                5,
                brand,
                type,
                color);
    }
}