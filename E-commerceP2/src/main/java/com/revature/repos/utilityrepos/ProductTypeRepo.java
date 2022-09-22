package com.revature.repos.utilityrepos;

import com.revature.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
    @Query(value = "SELECT * FROM product_type WHERE product_type = ?1", nativeQuery = true)
    ProductType getProductTypeByProductType(String productType);
}
