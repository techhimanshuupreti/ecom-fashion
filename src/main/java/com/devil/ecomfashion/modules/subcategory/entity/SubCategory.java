package com.devil.ecomfashion.modules.subcategory.entity;

import com.devil.ecomfashion.modules.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "sub_categories",uniqueConstraints = {
@UniqueConstraint(name = "UniqueNameAndCategory", columnNames = {"name", "category_id"})},
        indexes = @Index(columnList = "name,category_id,id"))
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,unique=true)
    private String name;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

}