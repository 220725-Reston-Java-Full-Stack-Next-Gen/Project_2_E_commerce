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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;

    @Column(name = "quantity", nullable = false)
    private int orderQuantity;

    @Column(name = "total_price", nullable = false)
    private double orderTotalPrice;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_shipped")
    private LocalDateTime dateShipped;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_user_id", referencedColumnName = "user_id")
    private User orderOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_payment_id", referencedColumnName = "payment_id")
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
    private OrderStatus orderStatus;

}
