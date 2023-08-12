package com.devil.ecomfashion.modules.category.repository;

import com.devil.ecomfashion.modules.category.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
