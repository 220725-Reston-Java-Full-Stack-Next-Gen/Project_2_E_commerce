package com.revature.repos.utilityrepos;

import com.revature.models.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductColorRepo extends JpaRepository<ProductColor, Integer> {
    @Query(value = "SELECT * FROM product_color WHERE color_hex_value = ?1", nativeQuery = true)
    ProductColor findProductColorByColorHexValue(String hex);
}
