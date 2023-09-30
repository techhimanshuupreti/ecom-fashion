package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.product.dto.ProductDTO;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public List<Product> find() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Optional<Product> findOne(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product create(ProductDTO productDTO) throws IOException {

        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setName(productDTO.getName());

        Optional<Category> category = categoryService.findOne(productDTO.getCategoryId());

        if (category.isEmpty()) {
            throw new RuntimeException();
        }

        product.setCategory(category.get());

        File fileInputStream = new File(System.getProperty("user.dir") + "/uploadFiles/" + productDTO.getFile().getOriginalFilename());
        product.setImagePath(fileInputStream.getPath());
        product.setLongDescription(productDTO.getLongDescription());
        product.setShortDescription(productDTO.getShortDescription());

        return productRepository.save(product);
    }
}
