package com.revature.services.utilityservices;

import com.revature.models.ProductColor;
import com.revature.repos.utilityrepos.ProductColorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductColorServiceImpl implements ProductColorService {
    @Autowired
    private ProductColorRepo productColorRepo;

    @Override
    public ProductColor add(ProductColor productColor) {
        return productColorRepo.save(productColor);
    }

    @Override
    public ProductColor findByHex(ProductColor productColor) {
        return productColorRepo.findProductColorByColorHexValue(productColor.getColorHexValue());
    }


}
