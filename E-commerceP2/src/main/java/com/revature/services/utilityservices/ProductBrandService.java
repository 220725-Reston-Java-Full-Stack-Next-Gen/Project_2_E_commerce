package com.revature.services.utilityservices;

import com.revature.models.ProductBrand;

public interface ProductBrandService {
    ProductBrand add(ProductBrand productBrand);
    ProductBrand getByBrand(ProductBrand productBrand);
}
