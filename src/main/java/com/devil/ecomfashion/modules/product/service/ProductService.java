package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.product.dto.ProductDTO;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${img-folder-name}")
    private String FOLDER_NAME;

    private final CategoryService categoryService;

    public List<Product> find(String name) {

        if (StringUtils.isEmpty(name))
            return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return productRepository.findByNameIgnoreCase(name,Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<Product> findOne(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product create(ProductDTO productDTO) {

        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setName(productDTO.getName());

        Category category = categoryService.findById(productDTO.getCategoryId());
        product.setCategory(category);

//        File fileInputStream = new File(System.getProperty("user.dir") + "/"+uploadFiles+"/" + productDTO.getFile().getOriginalFilename());
        System.out.println("Directory for Product images: " + System.getProperty("user.dir"));
        File fileInputStream = new File(System.getProperty("user.dir") + "/" + FOLDER_NAME + "/" + productDTO.getFile().getOriginalFilename());
        product.setImagePath(fileInputStream.getPath());
        product.setLongDescription(productDTO.getLongDescription());
        product.setShortDescription(productDTO.getShortDescription());

        return productRepository.save(product);
    }
}
