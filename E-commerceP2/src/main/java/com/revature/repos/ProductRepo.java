package com.revature.repos;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Product>  findAllProducts();

    @Query(value = "SELECT * FROM products JOIN product_brand_id ON product_brand_id WHERE product_brand=?1", nativeQuery = true)
    List<Product> findProductsByBrand(String brand);

    @Query(value = "SELECT * FROM products JOIN product_type_id ON product_type_id WHERE product_type=?1", nativeQuery = true)
    List<Product> findProductByType(String type);

    // crud operations on products

    @Query(value = "SELECT * FROM products where product_id=?1", nativeQuery = true)
    Product getProductById(int productId);

    @Query(value = "DELETE FROM products where product_id=?1", nativeQuery = true)
    boolean deleteProductById(int productId);


    @Query(value = "update products SET product_name =?1, product_price=?2, image_link=?3, " +
            "product_description=?4, product_rating=?5, product_brand_id=?6, product_type_id=?7, " +
            "product_color_id=?8 WHERE product_id=?9,", nativeQuery = true)
    boolean updateProductById(String productName, double productPrice,
                           String imageLink, String productDescription, int productRating,
                           ProductBrand productBrand, ProductType productType, ProductColor productColor
                            ,int productId);
    @Query(value = "INSERT INTO products (product_name, product_price, image_link, " +
            "product_description, product_rating, product_brand_id, product_type_id, " +
            "product_color_id VALUES (DEFAULT, ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    boolean insertProduct(String productName, double productPrice,
                              String image_Link, String productDescription, int productRating,
                              ProductBrand productBrand, ProductType productType, ProductColor productColor);



}
