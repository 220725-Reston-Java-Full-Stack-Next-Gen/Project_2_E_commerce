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
    private String productRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id", referencedColumnName = "product_category_id")
    private product_cateogory_id product_cateogory_id ;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_brand_id", referencedColumnName = "product_brand_id")
    private product_brand_id product_brand_id ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "product_type_id")
    private type_id product_type_id ; 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_color_id", referencedColumnName = "product_color_id")
    private product_color_id product_color_id ; 
    
    

}
