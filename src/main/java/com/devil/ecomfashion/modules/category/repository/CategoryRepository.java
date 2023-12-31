package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, String> {

    public List<DisplayCategory> findDistinctMainCategory();

    @Query()
    public List<DisplayCategory> findDistinctByType(String mainCategory);

    interface DisplayCategory {

        String getId();
        String getName();
        String getType();
    }
}

