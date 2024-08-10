package com.devil.ecomfashion.modules.subcategory.service;

import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.service.ProductService;
import com.devil.ecomfashion.modules.subcategory.dto.request.SubCategoryDTO;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.repository.SubCategoryRepository;
import com.devil.ecomfashion.utils.SubCategoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCatRepository;
    private final CategoryService catService;
    private final ProductService productService;

    public List<SubCategoryResponse> find(String name) {
        List<SubCategory> subCategories;

        if (StringUtils.isBlank(name)) {
            subCategories = subCatRepository.findAll();
        } else {
            subCategories = Collections.singletonList(subCatRepository.findByNameIgnoreCase(name));
        }

        return SubCategoryUtils.convertSubCategoryResponse(subCategories);
    }

    public SubCategoryResponse findById(long id) {
        Optional<SubCategory> subCategory = subCatRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new NullPointerException("no found sub-category");
        }
        return SubCategoryUtils.convertSubCategoryResponse(subCategory.get());
    }

    public SubCategory getById(long id) {
        Optional<SubCategory> subCategory = subCatRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new NullPointerException("no found sub-category");
        }
        return subCategory.get();
    }

    public Boolean delete(String name) {

        SubCategory subCategory = findOne(name);

        subCatRepository.deleteById(subCategory.getId());

        return true;
    }

    @Transactional
    public SubCategoryResponse create(SubCategoryDTO subCategoryDTO) {

        SubCategory subCategory = findOne(subCategoryDTO.getName());
        if(!ObjectUtils.isEmpty(subCategory)){
            throw new AlreadyExistException(subCategoryDTO.getName() + " sub category is not found");
        }

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        if(ObjectUtils.isEmpty(category))
            throw new ResourceNotFoundException("Category not found");

        subCategory = new SubCategory();
        subCategory.setCreatedAt(new Date());
        subCategory.setUpdatedAt(new Date());
        subCategory.setName(subCategoryDTO.getName());
        subCategory.setCategory(category);

        subCategory = subCatRepository.save(subCategory);
        return SubCategoryUtils.convertSubCategoryResponse(subCategory);
    }

    public SubCategory findOne(String name) {

        SubCategory subCategory = subCatRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(subCategory)) {
            return null;
        }

        return subCategory;
    }

    public SubCategoryResponse update(long id, SubCategoryDTO subCategoryDTO) {

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        if(ObjectUtils.isEmpty(category))
            throw new ResourceNotFoundException("Category not found");

        SubCategory updatedSubCategory = getById(id);
        updatedSubCategory.setName(subCategoryDTO.getName());
        updatedSubCategory.setUpdatedAt(new Date());
        updatedSubCategory.setCategory(category);

        updatedSubCategory = subCatRepository.save(updatedSubCategory);
        return SubCategoryUtils.convertSubCategoryResponse(updatedSubCategory);
    }


    public List<ProductResponse> findAllProductsBySubCategoryId(long id) {
        SubCategory subCategory = getById(id);
        return productService.getProductsBySubCategory(subCategory);
    }
}
