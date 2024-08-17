package com.devil.ecomfashion.modules.subcategory.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long>, PagingAndSortingRepository<SubCategory,Long> {

    SubCategory findByNameIgnoreCase(String name);

    List<SubCategory> findByNameIsContainingIgnoreCase(String name);

    List<SubCategory> findAllByCategory(Category category);

    Page<SubCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
