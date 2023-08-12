package com.devil.ecomfashion.modules.category.entity;

import com.devil.ecomfashion.modules.product.entiry.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    // Todo: it defines which like Mobile, Laptop (type: Electronics)
    @Column(nullable = false)
    private String name;

    // Todo: it defines which type of products like Electronics, Clothes,etc
    @Column(nullable = false, length = 120)
    private String type;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Category category)) return false;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(type, category.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
