package com.devil.ecomfashion.modules.subcategory.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {

    List<SubCategory> findAll();

    SubCategory findByNameIgnoreCase(String name);

    List<SubCategory> findByNameIsContainingIgnoreCase(String name);

    boolean deleteByNameIgnoreCase(String name);

    Optional<SubCategory> getByNameIgnoreCase(String name);

    List<SubCategory> findAllByCategory(Category category);

}
