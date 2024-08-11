package com.devil.ecomfashion.modules.cart.entity;

import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

@Entity
@Table(name = "carts")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false, updatable = false)
    private Date updatedAt;

    @Column(nullable = false)
    private User user;

    private int qty;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT '0.00'")
    private Double totalPrice;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Product product;

    public Double getTotalPrice() {
        if (ObjectUtils.isEmpty(this.totalPrice)) {
            if (ObjectUtils.isEmpty(this.product) || ObjectUtils.isEmpty(this.product.getPrice()) || this.product.getPrice() < 0) {
                return 0.0;
            }
            return this.product.getPrice() * this.qty;
        }
        return this.totalPrice;
    }

}