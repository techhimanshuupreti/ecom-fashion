package com.devil.ecomfashion.modules.product.repository;

import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findAll(Sort sort);
    List<Product> findByNameIgnoreCase(String name,Sort sort);

    List<Product> findAllBySubCategory(SubCategory subCategoryId);
    List<Product> findAllBySubCategoryIn(List<SubCategory> subCategories);

    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
