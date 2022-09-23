package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_type_id",nullable=false)
    private int productTypeId;

    @Column(name = "product_type", nullable = false, unique = true)
    private String productType;
<<<<<<< HEAD

    public ProductType(String productType) {
        this.productType = productType;
    }
=======
>>>>>>> Raphael
}


