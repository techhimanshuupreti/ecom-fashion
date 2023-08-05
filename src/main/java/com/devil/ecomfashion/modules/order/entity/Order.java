package com.devil.ecomfashion.modules.order.entity;

import com.devil.ecomfashion.modules.address.entity.Address;
import com.devil.ecomfashion.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "adress_id",nullable = false)
    private Address address;

    @OneToMany(orphanRemoval = true,mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<OrderQuantity> quantities = new ArrayList<>();

}
