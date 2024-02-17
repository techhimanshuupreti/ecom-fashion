package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> find(String name) {

        List<Category> categories;

        if (StringUtils.isBlank(name)) {
            categories = categoryRepository.findAll();
        } else {
            categories = Collections.singletonList(categoryRepository.findByNameIgnoreCase(name));
        }

        return categories;
    }

    public Optional<Category> findById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NullPointerException("no found category");
        }
        return category;
    }

    @Transactional
    public Category create(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public Category update(CategoryDTO categoryDTO) {

        Optional<Category> updatedCategory = categoryRepository.getCategoriesByNameIgnoreCase(categoryDTO.getName());

        if (updatedCategory.isEmpty()) {
            throw new ResourceNotFoundException("no found");
        }

        return updatedCategory.get();
    }

    public Boolean delete(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name);
        if(ObjectUtils.isEmpty(category)){
            throw new ResourceNotFoundException(name);
        }
        return categoryRepository.deleteByNameIgnoreCase(name);
    }
}
