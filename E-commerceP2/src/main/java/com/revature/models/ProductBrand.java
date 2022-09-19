package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_brand")
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_brand_id",nullable=false)
    private int productBrandId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "product_brand", nullable = false)
    private String productBrand;
}


