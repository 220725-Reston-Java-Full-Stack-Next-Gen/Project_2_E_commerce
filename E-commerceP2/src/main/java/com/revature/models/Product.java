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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_brand_id", referencedColumnName = "product_brand_id")
    private ProductBrand productBrand ; 
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id", referencedColumnName = "product_type_id")
    private ProductType productType ; 
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_color_id", referencedColumnName = "product_color_id")
<<<<<<< HEAD

    private ProductColor productColor ; 


=======
    private ProductColor productColor ; 

>>>>>>> Raphael
}
