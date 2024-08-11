package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Category findByNameIgnoreCase(String name);

    List<Category> findByNameIsContainingIgnoreCase(String name);

    boolean deleteByNameIgnoreCase(String name);

    Optional<Category> getByNameIgnoreCase(String name);

    @Modifying
    void deleteById(Long id);

//    @Query(value = "select distinct name from categories",nativeQuery = true)
//    List<String> findAllNames();

}

