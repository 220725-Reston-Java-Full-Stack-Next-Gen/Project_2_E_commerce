package com.revature.services.utilityservices;

import com.revature.models.ProductColor;

public interface ProductColorService {
    ProductColor add(ProductColor productColor);
    ProductColor findByHex(ProductColor productColor);
}
