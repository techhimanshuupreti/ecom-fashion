package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.projection.CategoryProjection;
import com.devil.ecomfashion.projection.Projection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {

    @Query("SELECT DISTINCT c.name,c.id FROM Category c")
    List<CategoryProjection> find();

    @Query("SELECT DISTINCT c.name,c.id FROM Category c where c.name=?1")
    List<CategoryProjection> findDistinctByName(String name);
}

