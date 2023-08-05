package com.devil.ecomfashion.modules.order.entity;

import com.devil.ecomfashion.modules.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_quantity")
public class OrderQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false,name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false,name = "order_id")
    private Order order;

    @Column(nullable = false,name = "quantity")
    private long qty;


}
