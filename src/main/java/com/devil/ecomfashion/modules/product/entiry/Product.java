package com.devil.ecomfashion.modules.product.entiry;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false, updatable = false)
    private Date updatedAt;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private SubCategory subCategory;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String longDescription;

    @Column(nullable = false)
    private String imagePath;

}
