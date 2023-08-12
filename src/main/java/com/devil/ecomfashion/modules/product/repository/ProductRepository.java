package com.devil.ecomfashion.modules.product.repository;

import com.devil.ecomfashion.modules.product.entiry.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
