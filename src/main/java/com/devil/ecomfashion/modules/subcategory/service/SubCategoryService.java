package com.devil.ecomfashion.modules.subcategory.service;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.subcategory.dto.request.SubCategoryDTO;
import com.devil.ecomfashion.modules.subcategory.dto.response.PageableSubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.repository.SubCategoryRepository;
import com.devil.ecomfashion.utils.PageableResponse;
import com.devil.ecomfashion.utils.SubCategoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCatRepository;
    private final CategoryService catService;

    @Transactional
    public PageableResponse<SubCategoryResponse> find(String name, int pageNo, int pageSize) {

        Page<SubCategory> subCategoryPage = null;
        Pageable pageable = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt")
                .descending().and(Sort.by("id").descending()));
        if (StringUtils.isBlank(name)) {
            subCategoryPage = subCatRepository.findAll(pageable);
        } else {
            subCategoryPage = subCatRepository.findAllByNameContainingIgnoreCase(name,pageable);
        }

        return SubCategoryUtils.convert(subCategoryPage);
    }

    @Transactional
    public SubCategoryResponse findById(long id) {
        SubCategory subCategory = getById(id);
        return SubCategoryUtils.convert(subCategory);
    }

    @Transactional
    public SubCategory getById(long id) {
        Optional<SubCategory> subCategory = subCatRepository.findById(id);
        if (subCategory.isEmpty()) {
            throw new ResourceNotFoundException(Message.NO_SUB_CATEGORY_FOUND);
        }
        return subCategory.get();
    }

    @Transactional
    public Boolean delete(String name) {

        SubCategory subCategory = findOne(name);

        subCatRepository.deleteById(subCategory.getId());

        return true;
    }

    @Transactional
    public SubCategoryResponse create(SubCategoryDTO subCategoryDTO) {

        SubCategory subCategory = findOne(subCategoryDTO.getName());
        if(!ObjectUtils.isEmpty(subCategory)){
            throw new AlreadyExistException(Message.ALREADY_FOUND.replace("%", subCategoryDTO.getName()));
        }

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        if(ObjectUtils.isEmpty(category))
            throw new ResourceNotFoundException(Message.NO_CATEGORY_FOUND);

        subCategory = new SubCategory();
        subCategory.setCreatedAt(new Date());
        subCategory.setUpdatedAt(new Date());
        subCategory.setName(subCategoryDTO.getName());
        subCategory.setCategory(category);

        subCategory = subCatRepository.save(subCategory);
        return SubCategoryUtils.convert(subCategory);
    }

    @Transactional
    public SubCategory findOne(String name) {

        SubCategory subCategory = subCatRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(subCategory)) {
            return null;
        }

        return subCategory;
    }

    @Transactional
    public SubCategoryResponse update(long id, SubCategoryDTO subCategoryDTO) {

        Category category = catService.findOne(subCategoryDTO.getCategoryName());
        if(ObjectUtils.isEmpty(category))
            throw new ResourceNotFoundException(Message.NO_CATEGORY_FOUND);

        SubCategory updatedSubCategory = getById(id);
        updatedSubCategory.setName(subCategoryDTO.getName());
        updatedSubCategory.setUpdatedAt(new Date());
        updatedSubCategory.setCategory(category);

        updatedSubCategory = subCatRepository.save(updatedSubCategory);
        return SubCategoryUtils.convert(updatedSubCategory);
    }


    public List<SubCategory> findAllByCategoryId(Long categoryId){
        Category category = catService.getById(categoryId);
        List<SubCategory> subCategories = subCatRepository.findAllByCategory(category);
        if (ObjectUtils.isEmpty(subCategories)) {
            return new ArrayList<>();
        }
        return subCategories;
    }
}
