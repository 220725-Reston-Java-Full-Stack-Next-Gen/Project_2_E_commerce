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
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private int orderDetailsID;

    @Column(name = "order_item_number", nullable = false)
    private int orderItemNumber;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;
}
