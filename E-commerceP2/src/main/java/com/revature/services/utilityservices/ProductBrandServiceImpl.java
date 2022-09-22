package com.revature.services.utilityservices;

import com.revature.models.ProductBrand;
import com.revature.repos.utilityrepos.ProductBrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {
    @Autowired
    private ProductBrandRepo productBrandRepo;

    @Override
    public ProductBrand add(ProductBrand productBrand) {
        return productBrandRepo.save(productBrand);
    }

    @Override
    public ProductBrand getByBrand(ProductBrand productBrand) {
        return productBrandRepo.getProductBrandByProductBrand(productBrand.getProductBrand());
    }
}
