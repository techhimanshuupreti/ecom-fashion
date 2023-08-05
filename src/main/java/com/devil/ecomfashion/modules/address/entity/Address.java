package com.devil.ecomfashion.modules.address.entity;

import com.devil.ecomfashion.modules.user.entity.User;
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
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 512)
    private String addressLine1;

    @Column(nullable = false,length = 512)
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false,length = 75)
    private String country;

    private String postalCode;

    private boolean active;


    private User user;


}
