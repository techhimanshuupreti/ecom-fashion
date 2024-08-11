package com.devil.ecomfashion.modules.product.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.product.dto.request.ProductDTO;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<ProductResponse> find(String name, int pageIndex, int pageSize) {
        log.info("fetching products with name {}", name);
        List<Product> products = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("createdAt").descending());
        if (StringUtils.isEmpty(name)) {
            pageRequest.withSort((Sort.by("name").ascending()));
            Pageable sortedByCreatedAtDescAndNameAsc = pageRequest.withSort((Sort.by("name").ascending()));
            products = productRepository.findAllByNameContainingIgnoreCase(name, sortedByCreatedAtDescAndNameAsc);
        } else {
            pageRequest.withSort((Sort.by("id").ascending()));
            Pageable sortedByCreatedAtDescAndIdAsc = pageRequest.withSort((Sort.by("name").ascending()));
            products = productRepository.findAllByNameContainingIgnoreCase(name, sortedByCreatedAtDescAndIdAsc);
        }
        return ProductUtils.convertProductResponse(products);
    }

    public ProductResponse findOne(long id) {
        Product product = getById(id);
        return ProductUtils.convertProductResponse(product);
    }

    public Product getById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new ResourceNotFoundException("product not found");

        return product.get();
    }

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
        return ProductUtils.convertProductResponse(product);
    }

    public List<ProductResponse> getProductsBySubCategory(SubCategory subCategoryId) {
        List<Product> products = productRepository.findAllBySubCategory(subCategoryId);
        if (ObjectUtils.isEmpty(products))
            return new ArrayList<>();

        return ProductUtils.convertProductResponse(products);
    }

    public List<ProductResponse> getProductsBySubCategory(Long subCategoryId) {
        SubCategory subCategory = subCategoryService.getById(subCategoryId);
        List<Product> products = productRepository.findAllBySubCategory(subCategory);
        if (ObjectUtils.isEmpty(products))
            return new ArrayList<>();

        return ProductUtils.convertProductResponse(products);
    }

    public List<ProductResponse> getProductsBySubCategory(List<SubCategory> subCategories) {
        List<Product> products = productRepository.findAllBySubCategoryIn(subCategories);
        if (ObjectUtils.isEmpty(products))
            return new ArrayList<>();

        return ProductUtils.convertProductResponse(products);
    }


    public List<ProductResponse> getProductsByCategory(Long id) {

        List<SubCategory> subCategories = subCategoryService.findAllByCategoryId(id);

        return getProductsBySubCategory(subCategories);
    }

    public Boolean delete(long id) {
        productRepository.deleteById(id);
        return true;
    }

    public ProductResponse updateProduct(long id, ProductDTO productDTO) {

        Product product = getById(id);

        boolean isUpdated = false;

        if (!ObjectUtils.isEmpty(productDTO.getName()) && !productDTO.getName().equals(product.getName())) {
            product.setName(productDTO.getName());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(productDTO.getDescription()) && !productDTO.getDescription().equals(product.getDescription())) {
            product.setDescription(productDTO.getDescription());
            isUpdated = true;
        }

        if (!ObjectUtils.isEmpty(productDTO.getFile()) && !Objects.equals(productDTO.getFile().getOriginalFilename(), productDTO.getFile().getOriginalFilename())) {
            product.setImagePath(productDTO.getFile().getOriginalFilename());
            isUpdated = true;
        }

        if (ObjectUtils.isEmpty(productDTO.getSubcategoryId()) && !productDTO.getSubcategoryId().equals(product.getId())) {
            SubCategory subCategory = subCategoryService.getById(productDTO.getSubcategoryId());
            product.setSubCategory(subCategory);
            isUpdated = true;
        }
        if (isUpdated) {
            product.setUpdatedAt(new Date());
        }

        return ProductUtils.convertProductResponse(product);
    }

}
