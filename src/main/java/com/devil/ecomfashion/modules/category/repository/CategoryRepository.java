package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category,Long> {

    Category findByNameIgnoreCase(String name);

    @Modifying
    void deleteById(Long id);

    Page<Category> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}

