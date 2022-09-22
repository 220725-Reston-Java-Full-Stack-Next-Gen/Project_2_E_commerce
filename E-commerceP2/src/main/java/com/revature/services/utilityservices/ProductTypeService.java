package com.revature.services.utilityservices;

import com.revature.models.ProductType;

public interface ProductTypeService {
    ProductType add(ProductType productType);
    ProductType getByType(ProductType productType);
}
