package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category findByNameIgnoreCase(String name);

    List<Category> findByNameIsContainingIgnoreCase(String name);

    boolean deleteByNameIgnoreCase(String name);

    Optional<Category> getCategoriesByNameIgnoreCase(String name);
}

