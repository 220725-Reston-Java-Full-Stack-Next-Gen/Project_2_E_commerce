package com.revature.repos;

import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value="SELECT * FROM products")
    public boolean findAllProducts();

    @Query(value="SELECT * FROM products JOIN product_brand_id ON product_brand_id WHERE product_brand=?1")
    public boolean findProductByBrand(String brand);
    @Query(value="SELECT * FROM products JOIN product_type_id ON product_type_id WHERE product_type=?1")
    public boolean findProductByType(String type);

    // crud operations on products
//
//    @Query(value="SELECT * FROM products where product_id=?1")
//    public boolean getProductById(int product_id);
//
//    @Query(value="SELECT * FROM products where product_id=?1")
//    public boolean getProductById(int product_id);
//
//
//    @Query(value="update products SET ")
//    public boolean getProductById(int product_id);

}
