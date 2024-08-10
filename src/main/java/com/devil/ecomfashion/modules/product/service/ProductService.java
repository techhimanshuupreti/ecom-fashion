package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.product.dto.request.ProductDTO;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.repository.ProductRepository;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.service.SubCategoryService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${img-folder-name}")
    private String FOLDER_NAME;

    private final SubCategoryService subCategoryService;

    @Transactional(readOnly = true)
    public List<ProductResponse> find(String name) {
        log.info("fetching products with name {}", name);
        List<Product> products = new ArrayList<>();
        if (StringUtils.isEmpty(name)) {
            products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }else {
            products = productRepository.findByNameIgnoreCase(name, Sort.by(Sort.Direction.DESC, "id"));
        }
        return convertProductResponse(products);
    }

    public ProductResponse findOne(long id) {
        Product product = getById(id);
        return convertProductResponse(product);
    }

    public Product getById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new ResourceNotFoundException("product not found");

        return product.get();
    }

    @Transactional
    public ProductResponse create(ProductDTO productDTO) {
        log.info("saving the product {}", productDTO);
        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setName(productDTO.getName());

        SubCategory subCategory = subCategoryService.getById(productDTO.getSubcategoryId());
        product.setSubCategory(subCategory);

//        File fileInputStream = new File(System.getProperty("user.dir") + "/"+uploadFiles+"/" + productDTO.getFile().getOriginalFilename());
        System.out.println("Directory for Product images: " + System.getProperty("user.dir"));
        File fileInputStream = new File(System.getProperty("user.dir") + "/" + FOLDER_NAME + "/" + productDTO.getFile().getOriginalFilename());
        product.setImagePath(fileInputStream.getPath());
        product.setDescription(productDTO.getDescription());

        product = productRepository.save(product);
        return convertProductResponse(product);
    }

    public ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    public List<ProductResponse> convertProductResponse(List<Product> products) {

        return products.stream().map(product -> ProductResponse.builder()
                        .name(product.getName())
                        .id(product.getId()).build())
                .collect(Collectors.toList());
    }
}
