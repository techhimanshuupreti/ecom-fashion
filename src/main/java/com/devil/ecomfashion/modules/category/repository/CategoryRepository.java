package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.projection.CategoryProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT name,id FROM Category")
    List<CategoryProjection> find();

    @Query("SELECT DISTINCT name as name,id FROM Category where name=?1")
    List<CategoryProjection> findByNameIgnoreCase(String name);

    boolean deleteByNameIgnoreCase(String name);

    @Query("SELECT c FROM Category c where c.name=?1")
    Optional<Category> getCategoriesByNameIgnoreCase(String name);
}

