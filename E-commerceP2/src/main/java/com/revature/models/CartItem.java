package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> Raphael
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_items")
<<<<<<< HEAD

=======
>>>>>>> Raphael
public class CartItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int cartItemID;
    
    @Column(name = "cart_item_number", nullable = false)
    private int cartItemNumber;
    
    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;
    
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;
    
<<<<<<< HEAD
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
=======
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.MERGE)
>>>>>>> Raphael
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

}
