package com.devil.ecomfashion.modules.product.repository;

import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll(Sort sort);
    List<Product> findByNameIgnoreCase(String name,Sort sort);

    List<Product> findAllBySubCategory(SubCategory subCategoryId);
    List<Product> findAllBySubCategoryIn(List<SubCategory> subCategories);
}
