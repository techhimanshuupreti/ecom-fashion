package com.devil.ecomfashion.modules.subcategory.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.subcategory.dto.SubCategoryDTO;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCatRepository;
    private final CategoryService catService;

    public List<SubCategory> find(String name) {
        List<SubCategory> subCategories;

        if (StringUtils.isBlank(name)) {
            subCategories = subCatRepository.findAll();
        } else {
            subCategories = Collections.singletonList(subCatRepository.findByNameIgnoreCase(name));
        }

        return subCategories;
    }

    public SubCategory findById(long id) {
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
    public SubCategory create(SubCategoryDTO subCategoryDTO) {

        SubCategory subCategory = new SubCategory();
        subCategory.setCreatedAt(new Date());
        subCategory.setUpdatedAt(new Date());
        subCategory.setName(subCategoryDTO.getName());

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        subCategory.setCategory(category);

        return subCatRepository.save(subCategory);
    }

    public SubCategory findOne(String name) {

        SubCategory subCategory = subCatRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(subCategory)) {
            throw new ResourceNotFoundException(name + " sub category is not found");
        }

        return subCategory;
    }

    public SubCategory update(long id, SubCategoryDTO subCategoryDTO) {

        SubCategory updatedSubCategory = findById(id);
        updatedSubCategory.setName(subCategoryDTO.getName());
        updatedSubCategory.setUpdatedAt(new Date());

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        updatedSubCategory.setCategory(category);

        return subCatRepository.save(updatedSubCategory);
    }
}
