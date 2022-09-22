package com.revature.services.utilityservices;

import com.revature.models.ProductType;
import com.revature.repos.utilityrepos.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Override
    public ProductType add(ProductType productType) {
        return productTypeRepo.save(productType);
    }

    @Override
    public ProductType getByType(ProductType productType) {
        return productTypeRepo.getProductTypeByProductType(productType.getProductType());
    }
}
