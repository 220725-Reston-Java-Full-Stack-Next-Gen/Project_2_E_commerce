package com.revature.controllers;

import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import com.revature.services.utilityservices.ProductBrandService;
import com.revature.services.utilityservices.ProductColorService;
import com.revature.services.utilityservices.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/api/util-products")
public class OrderUtilController {
    @Autowired
    private ProductBrandService productBrandService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductColorService productColorService;

    @PostMapping("/add-colors")
    public @ResponseBody String addProductColors(@RequestBody List<ProductColor> productColors) {
        System.out.println(productColors);
        for (ProductColor color : productColors) {
            //System.out.println(productColorService.findByHex(color));
            if (productColorService.findByHex(color) == null) {
                productColorService.add(color);
            }
        }
        return "Success adding colors";
    }

    @PostMapping("/add-brands")
    public @ResponseBody String addProductBrands(List<ProductBrand> productBrands) {
        System.out.println(productBrands);
        for (ProductBrand brand: productBrands) {
            productBrandService.add(brand);
        }
        return "Success adding brands";
    }

    @PostMapping("/add-types")
    public @ResponseBody String addProductTypes(List<ProductType> productTypes) {
        for (ProductType type: productTypes) {
            productTypeService.add(type);
        }
        return "Success adding types";
    }
}
