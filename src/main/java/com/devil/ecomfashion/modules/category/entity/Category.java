package com.devil.ecomfashion.modules.category.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    // Todo: it defines which type of products like Electronics, Clothes,etc
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof Category category)) return false;
        return id == category.id && Objects.equals(name, category.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
