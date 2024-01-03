package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {

    @Query("SELECT DISTINCT c.name,c.type,c.id FROM Category c")
    public List<DisplayCategory> find();

    public List<DisplayCategory> findDistinctByType(String mainCategory);

    interface DisplayCategory {

        String getId();
        String getName();
        String getType();
    }
}

