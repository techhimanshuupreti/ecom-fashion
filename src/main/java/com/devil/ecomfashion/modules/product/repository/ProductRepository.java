package com.devil.ecomfashion.modules.product.repository;

import com.devil.ecomfashion.modules.product.entiry.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll(Sort sort);
    List<Product> findByNameIgnoreCase(String name,Sort sort);
}
