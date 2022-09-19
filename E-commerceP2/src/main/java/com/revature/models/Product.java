package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
<<<<<<< HEAD
@Table(name = "product")
=======
@Table(name = "products")
>>>>>>> b64488770d7472ecacb44bf4da484f3210176995
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
<<<<<<< HEAD
    private String productRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id", referencedColumnName = "product_category_id")
    private product_cateogory_id product_cateogory_id ;
=======
    private int productRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id", referencedColumnName = "product_category_id")
    private ProductCategory productCategory ;
>>>>>>> b64488770d7472ecacb44bf4da484f3210176995


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_brand_id", referencedColumnName = "product_brand_id")
<<<<<<< HEAD
    private product_brand_id product_brand_id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "product_type_id")
    private type_id product_type_id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_color_id", referencedColumnName = "product_color_id")
    private product_color_id product_color_id;
=======
    private ProductBrand productBrand ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "product_type_id")
    private ProductType productType ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_color_id", referencedColumnName = "product_color_id")
    private ProductColor productColor ; 
>>>>>>> b64488770d7472ecacb44bf4da484f3210176995
    
    

}