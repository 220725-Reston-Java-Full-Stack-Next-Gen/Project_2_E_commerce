package com.revature.controllers;


import java.util.List;

import com.revature.models.Product;
import com.revature.models.ProductBrand;
import com.revature.models.ProductColor;
import com.revature.models.ProductType;
import com.revature.services.ProductService;
import com.revature.services.utilityservices.ProductBrandService;
import com.revature.services.utilityservices.ProductColorService;
import com.revature.services.utilityservices.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;

>>>>>>> Raphael
@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://127.0.0.1:5500")
public class ProductController {
//todo: use client messages, and change urls
    @Autowired
    private ProductService productService;

//    @Autowired
//    private ProductColorService productColorService;
//
//    @Autowired
//    private ProductBrandService productBrandService;
//
//    @Autowired
//    private ProductTypeService productTypeService;

    @GetMapping("/products")
<<<<<<< HEAD
=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
>>>>>>> Raphael
    public @ResponseBody List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products-by-brand")
<<<<<<< HEAD
    public List<Product> getProductsByBrand(@RequestParam String brand){
=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public List<Product> getProductsByBrand(@RequestParam String brand, HttpServletRequest request){
>>>>>>> Raphael
        return productService.getProductsByBrand(brand);
    }

    @GetMapping("/products-by-type")
<<<<<<< HEAD
    public List<Product> getProductsByType(@RequestParam String type){
=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public List<Product> getProductsByType(@RequestParam String type, HttpServletRequest request){
        System.out.println(request.getSession().getId());
>>>>>>> Raphael
        return productService.getProductsByType(type);
    }

    @GetMapping("/product-by-id")
<<<<<<< HEAD
=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
>>>>>>> Raphael
    public Product getProductByID(@RequestParam int id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete-product")
<<<<<<< HEAD
        public boolean deleteProductById(@RequestParam int id){
        return productService.deleteProductById(id);
    }

=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.DELETE, allowedHeaders = "*")
    public boolean deleteProductById(@RequestParam int id){
        return productService.deleteProductById(id);
    }

    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.PUT, allowedHeaders = "*")
>>>>>>> Raphael
    @PutMapping("/update-product")
    public boolean updateProductById(@RequestBody String productName,
                                     @RequestBody double productPrice,
                                     @RequestBody String imageLink,
                                     @RequestBody String productDescription,
                                     @RequestBody int productRating,
                                     @RequestBody ProductBrand productBrand,
                                     @RequestBody ProductType productType,
                                     @RequestBody ProductColor productColor,
                                     @RequestBody int productId){
        return productService.updateProductById(productName, productPrice,
                imageLink, productDescription, productRating, productBrand,
                productType, productColor, productId);
    }

    @PostMapping("add-product")
<<<<<<< HEAD
    public Product addProduct(@RequestBody Product product) {
=======
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
    public @ResponseBody Product addProduct(@RequestBody Product product) {
>>>>>>> Raphael

//        , @RequestParam String type, @RequestParam String color, @RequestParam String brand
//        ProductColor pc = new ProductColor();
//        pc.setColorHexValue("#" + color);
//        product.setProductColor(productColorService.findByHex(pc));
//
//        ProductBrand pb = new ProductBrand();
//        pb.setProductBrand(brand);
//        product.setProductBrand(productBrandService.getByBrand(pb));
//
//
//        ProductType pt = new ProductType();
//        pt.setProductType(type);
//        product.setProductType(productTypeService.getByType(pt));

        System.out.println(product);
        return productService.addProduct(product);

    }
<<<<<<< HEAD
=======

    @GetMapping("/search")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody List<Product> search(@RequestParam String query) {
        return productService.searchProduct(query);
    }

>>>>>>> Raphael
//
//    @PostMapping("/populate-products")
//    public String populateProducts(@RequestBody List<Product> products) {
//
//        for (Product product: products) {
//            System.out.println(product);
//            productService.addProduct(product);
//        }
//        return "Success populating products";
//    }

}
