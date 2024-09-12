package com.devil.ecomfashion.modules.order.entity;

import com.devil.ecomfashion.modules.cart.entity.Cart;
import com.devil.ecomfashion.modules.product.entiry.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Long userId;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
    private Double totalPrice;

    private boolean isDeleted;

    private Long paymentId;
    private Long deliveryId;

}
