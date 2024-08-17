package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.product.dto.request.ProductDTO;
import com.devil.ecomfashion.modules.product.dto.request.UpdateProductDTO;
import com.devil.ecomfashion.modules.product.dto.response.PageableProductResponse;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.repository.ProductRepository;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.service.SubCategoryService;
import com.devil.ecomfashion.utils.ProductUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${img-folder-name}")
    private String FOLDER_NAME;

    private final SubCategoryService subCategoryService;

    @Transactional
    public PageableProductResponse find(String name, int pageNo, int pageSize) {
        log.info("fetching products with name {}", name);
        Page<Product> productPage = null;
        Pageable pageable = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt")
                .descending().and(Sort.by("id").descending()));
        if (!StringUtils.isEmpty(name)) {
            productPage = productRepository.findAllByNameContainingIgnoreCase(name, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        return ProductUtils.convert(productPage);
    }

    public ProductResponse findOne(long id) {
        Product product = getById(id);
        return ProductUtils.convert(product);
    }

    @Transactional
    public Product getById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ResourceNotFoundException("product not found");

        return product.get();
    }

    @Transactional
    public ProductResponse create(ProductDTO productDTO) {
        log.info("saving the product {}", productDTO);
        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        SubCategory subCategory = subCategoryService.getById(productDTO.getSubcategoryId());
        product.setSubCategory(subCategory);

//        File fileInputStream = new File(System.getProperty("user.dir") + "/"+uploadFiles+"/" + productDTO.getFile().getOriginalFilename());
        System.out.println("Directory for Product images: " + System.getProperty("user.dir"));
        File fileInputStream = new File(System.getProperty("user.dir") + "/" + FOLDER_NAME + "/" + productDTO.getFile().getOriginalFilename());
        product.setImagePath(fileInputStream.getPath());
        product.setDescription(productDTO.getDescription());

        product = productRepository.save(product);
        return ProductUtils.convert(product);
    }

    @Transactional
    public PageableProductResponse getProductsBySubCategory(Long subCategoryId, int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt").descending());
        SubCategory subCategory = subCategoryService.getById(subCategoryId);
        Page<Product> productPage = productRepository.findAllBySubCategoryIn(Collections.singletonList(subCategory), pageRequest);
        return ProductUtils.convert(productPage);
    }

    @Transactional
    public PageableProductResponse getProductsBySubCategory(List<SubCategory> subCategories, int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt").descending());
        Page<Product> productPage = productRepository.findAllBySubCategoryIn(subCategories, pageRequest);
        return ProductUtils.convert(productPage);
    }

    @Transactional
    public PageableProductResponse getProductsByCategory(Long id, int pageNo, int pageSize) {
        List<SubCategory> subCategories = subCategoryService.findAllByCategoryId(id);
        return getProductsBySubCategory(subCategories, pageNo, pageSize);
    }

    @Transactional
    @Modifying
    public Boolean delete(long id) {
        productRepository.deleteById(id);
        return true;
    }

    public ProductResponse updateProduct(long id, UpdateProductDTO updateProductDTO) {

        Product product = getById(id);

        boolean isUpdated = false;

        if (!ObjectUtils.isEmpty(updateProductDTO.getName())) {
            product.setName(updateProductDTO.getName());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(updateProductDTO.getDescription())) {
            product.setDescription(updateProductDTO.getDescription());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(updateProductDTO.getPrice())) {
            product.setPrice(updateProductDTO.getPrice());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(updateProductDTO.getFile())) {
            product.setImagePath(updateProductDTO.getFile().getOriginalFilename());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(updateProductDTO.getSubcategoryId())) {
            SubCategory subCategory = subCategoryService.getById(updateProductDTO.getSubcategoryId());
            product.setSubCategory(subCategory);
            isUpdated = true;
        }
        if (isUpdated) {
            product.setUpdatedAt(new Date());
            productRepository.save(product);
        }

        return ProductUtils.convert(product);
    }

}
