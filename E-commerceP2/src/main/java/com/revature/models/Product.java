package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int ProductID;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "image_link", nullable = false)
    private String image_Link;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_rating")
    private int productRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_brand_id", referencedColumnName = "product_brand_id")
    private ProductBrand productBrand ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "product_type_id")
    private ProductType productType ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_color_id", referencedColumnName = "product_color_id")
    private ProductColor productColor ;

    public Product(String productName, double productPrice, String image_Link, String productDescription, int productRating, ProductBrand productBrand, ProductType productType, ProductColor productColor) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.image_Link = image_Link;
        this.productDescription = productDescription;
        this.productRating = productRating;
        this.productBrand = productBrand;
        this.productType = productType;
        this.productColor = productColor;
    }
}
