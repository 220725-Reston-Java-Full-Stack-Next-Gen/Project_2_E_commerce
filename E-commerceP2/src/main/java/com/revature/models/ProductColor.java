package com.revature.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_color")
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_color_id")
    private int productColorID;

    @Column(name = "color_hex_value", nullable = false, unique = true)
    private String colorHexValue;
    
    @Column(name = "color_name", nullable = false)
    private String colorName;

<<<<<<< HEAD

    public ProductColor(String colorHexValue, String colorName) {
        this.colorHexValue = colorHexValue;
        this.colorName = colorName;
    }
=======
>>>>>>> Raphael
}
