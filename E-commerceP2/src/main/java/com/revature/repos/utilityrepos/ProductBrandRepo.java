package com.revature.repos.utilityrepos;

import com.revature.models.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductBrandRepo extends JpaRepository<ProductBrand, Integer> {
    @Query(value = "SELECT * FROM product_brand WHERE product_brand = ?1", nativeQuery = true)
    ProductBrand getProductBrandByProductBrand(String productBrand);
}
