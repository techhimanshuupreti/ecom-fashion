package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> find() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> findOne(long id) {
        return productRepository.findById(id);
    }
}
